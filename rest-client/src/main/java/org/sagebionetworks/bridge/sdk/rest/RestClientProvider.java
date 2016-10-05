package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.models.accounts.StudyUserCredentials;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by liujoshua on 10/4/16.
 */
public class RestClientProvider {
    private final HttpUrl baseUrl;
    private final Retrofit authenticatedRetrofit;
    private final AuthenticationHandler authenticationHandler;
    private final HeaderHandler headerHandler;

    private AuthenticationService authenticationService;
    private UploadService uploadService;

    public RestClientProvider(String baseUrl, String userAgent, StudyUserCredentials studyUserCredentials) {
        this.baseUrl = HttpUrl.parse(baseUrl);
        this.headerHandler = new HeaderHandler(userAgent);

        OkHttpClient unAuthedBridgeClient = new OkHttpClient.Builder()
                .addInterceptor(headerHandler)
                .build();

        Retrofit unauthenticatedRetrofit =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(unAuthedBridgeClient)
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

        AuthenticationService authenticationService = unauthenticatedRetrofit.create(AuthenticationService.class);

        authenticationHandler = new AuthenticationHandler(studyUserCredentials, authenticationService);

        // AuthenticationHandler auth must be registered as both an interceptor and an authenticator
        OkHttpClient authedBridgeClient =
                new OkHttpClient.Builder()
                        .authenticator(authenticationHandler)
                        .addInterceptor(authenticationHandler)
                        .addInterceptor(headerHandler)
                        .build();

        this.authenticatedRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(authedBridgeClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    // allow test to access authentication handler
    AuthenticationHandler getAuthenticationHandler() {
        return authenticationHandler;
    }

    public AuthenticationService getAuthenticationService() {
        if (authenticationService == null) {
            authenticationService = authenticatedRetrofit.create(AuthenticationService.class);
        }
        return authenticationService;
    }

    public UploadService getUploadService() {
        if (uploadService == null) {
            uploadService = authenticatedRetrofit.create(UploadService.class);
        }
        return uploadService;
    }
}
