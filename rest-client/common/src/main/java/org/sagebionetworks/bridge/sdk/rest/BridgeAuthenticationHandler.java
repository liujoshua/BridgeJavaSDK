package org.sagebionetworks.bridge.sdk.rest;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Route;
import org.sagebionetworks.bridge.sdk.models.users.SignInBody;

/**
 * Created by liujoshua on 4/8/16.
 */
public class BridgeAuthenticationHandler implements Authenticator, Interceptor {
    private static final int MAX_TRIES = 3;
    private final SignInBody signInBody;
    private final AuthenticationService authenticationService;
    private final AtomicReference<Session> sessionAtomicReference;
    private final ThreadLocal<Integer> tryCount;

    public BridgeAuthenticationHandler(SignInBody signInBody,
                                       AuthenticationService authenticationService) {
        this.signInBody = signInBody;
        this.authenticationService = authenticationService;
        this.sessionAtomicReference = new AtomicReference<>();
        this.tryCount = new ThreadLocal<>();
        this.tryCount.set(0);
    }

    @Override
    public okhttp3.Request authenticate(Route route, okhttp3.Response response) throws IOException {
        retrofit2.Response<Session> signInResponse;

        int tryCount = this.tryCount.get();
        System.out.println(tryCount);
        if (tryCount >= MAX_TRIES) {
            this.tryCount.set(0);
            return null;

        }
        this.tryCount.set(tryCount + 1);

        signInResponse = authenticationService.signIn(signInBody).execute();

        if (signInResponse.isSuccessful()) {
            // token returned in any valid response should remain valid for a while
            sessionAtomicReference.set(signInResponse.body());

        }
        // interceptor was already triggered for this request, add new headers
        return addBridgeHeaders(response.request());
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = chain.proceed(addBridgeHeaders(chain.request()));

        // if we reach this part of the code, we didn't get a 401 and the authenticator did its job
        this.tryCount.set(Integer.valueOf(0));
        return response;
    }

    private okhttp3.Request addBridgeHeaders(okhttp3.Request request) {
        Session session = sessionAtomicReference.get();
        String sessionToken = session == null ? "" : session.sessionToken;

        return request
                .newBuilder()
                .header("User-Agent", "Mole Mapper jl Android test")
                .header("Bridge-Session", sessionToken)
                .build();
    }

    // allow tests to inspect current session token
    String getSessionToken() {
        Session session = sessionAtomicReference.get();
        return session == null ? "" : session.sessionToken;
    }
}
