package org.sagebionetworks.bridge.sdk.rest;

import okhttp3.OkHttpClient;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentials;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by liujoshua on 6/9/16.
 */
public class BridgeServices {
    public Retrofit getAuthenticatedRetrofit(String baseUrl, Converter.Factory converterFactory, StudyUserCredentials studyUserCredentials) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory).build();

                AuthenticationService authenticationService = retrofit.create(AuthenticationService.class);

        AuthenticationHandler authenticationHandler = new AuthenticationHandler(studyUserCredentials, authenticationService);

        // AuthenticationHandler auth must be registered as both an interceptor and an authenticator
        OkHttpClient authedHttpClient =
                new OkHttpClient.Builder().authenticator(authenticationHandler).addInterceptor(authenticationHandler).build();

        return new Retrofit.Builder().baseUrl(baseUrl).client(authedHttpClient)
                .addConverterFactory(converterFactory).build();
    }
}
