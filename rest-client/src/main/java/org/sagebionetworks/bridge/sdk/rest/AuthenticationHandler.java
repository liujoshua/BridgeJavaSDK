package org.sagebionetworks.bridge.sdk.rest;

import java.io.IOException;

import org.sagebionetworks.bridge.sdk.UserSession;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyUserCredentials;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Route;

/**
 * Created by liujoshua on 4/8/16.
 */
public class AuthenticationHandler implements Authenticator, Interceptor {
    private static final int MAX_TRIES = 3;

    private final StudyUserCredentials studyUserCredentials;
    private final AuthenticationService authenticationService;
    private UserSession userSession;
    private int tryCount;

    public AuthenticationHandler(StudyUserCredentials studyUserCredentials, AuthenticationService authenticationService) {
        this.studyUserCredentials = studyUserCredentials;
        this.authenticationService = authenticationService;
    }


    @Override
    public okhttp3.Request authenticate(Route route, okhttp3.Response response) throws IOException {
        // if we reach this part of the code, the server had returned a 401 and userSession is invalid
        this.userSession = null;

        if (tryCount >= MAX_TRIES) {
            this.tryCount = 0;
            return null;
        }
        tryCount++;

        if(studyUserCredentials == null || authenticationService == null) {
            return null;
        }

        retrofit2.Response<UserSession> signInResponse;
        signInResponse = authenticationService.signIn(studyUserCredentials).execute();

        if (signInResponse.isSuccessful()) {
            // token returned in any valid response should remain valid for a while
            this.userSession = signInResponse.body();
        }

        // interceptor was already triggered for this request, add new headers
        return addBridgeHeaders(response.request());
    }


    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = chain.proceed(addBridgeHeaders(chain.request()));

        // if we reach this part of the code, we didn't get a 401 and the authenticator did its job
        this.tryCount = 0;

        return response;
    }

    private okhttp3.Request addBridgeHeaders(okhttp3.Request request) throws IOException {
        String sessionToken = getSessionToken();

        if(sessionToken == null) {
            return request;
        }
        return request.newBuilder()
                .header("Bridge-Session", sessionToken)
                .build();
    }

    // allow tests to inspect current session token
    String getSessionToken() {
        return this.userSession == null ? null : this.userSession.getSessionToken();
    }
}
