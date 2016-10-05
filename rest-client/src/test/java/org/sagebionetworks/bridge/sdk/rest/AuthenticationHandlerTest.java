package org.sagebionetworks.bridge.sdk.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.sagebionetworks.bridge.sdk.models.accounts.StudyUserCredentials;
import org.sagebionetworks.bridge.sdk.models.upload.UploadRequest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by liujoshua on 4/8/16.
 */
public class AuthenticationHandlerTest {
    private AuthenticationHandler authenticationHandler;
    private Retrofit bridgeAuthenticatedRetrofit;

    @Before
    public void before() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://webservices-develop.sagebridge.org")
                .addConverterFactory(JacksonConverterFactory.create()).build();

        StudyUserCredentials credentials = new StudyUserCredentials("api", "bridgeit@sagebase.org", "a6rutHebadeX7Ch");
        AuthenticationService authenticationService = retrofit.create(AuthenticationService.class);

        authenticationHandler = new AuthenticationHandler(credentials, authenticationService);

        // AuthenticationHandler auth must be registered as both an interceptor and an authenticator
        OkHttpClient authedHttpClient =
                new OkHttpClient.Builder().authenticator(authenticationHandler).addInterceptor(authenticationHandler).build();

        bridgeAuthenticatedRetrofit = new Retrofit.Builder().baseUrl("https://webservices-develop.sagebridge.org").client(authedHttpClient)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    @Test
    public void testReauthOnLogout() throws Exception {
        UploadRequest dummyUploadRequest = new UploadRequest
                .Builder()
                .withContentLength(1)
                .withContentMd5("hash")
                .withName("name")
                .withContentType("type")
                .build();

        UploadService svc = bridgeAuthenticatedRetrofit.create(UploadService.class);
        svc.requestUploadSession(dummyUploadRequest).execute();

        String authToken = authenticationHandler.getSessionToken();
        Assert.assertNotNull(authToken);
        Assert.assertTrue(authToken.length() > 0);

        // expires token
        AuthenticationService authenticatedAuthService = bridgeAuthenticatedRetrofit.create(AuthenticationService.class);
        authenticatedAuthService.signOut().execute();

        svc.requestUploadSession(dummyUploadRequest).execute();

        // verify new session token is now being used
        String newAuthToken = authenticationHandler.getSessionToken();
        Assert.assertNotNull(newAuthToken);
        Assert.assertTrue(newAuthToken.length() > 0);
        Assert.assertNotEquals(authToken, newAuthToken);
    }

}
