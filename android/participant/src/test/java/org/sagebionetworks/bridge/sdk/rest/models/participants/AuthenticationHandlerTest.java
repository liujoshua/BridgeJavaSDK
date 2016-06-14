package org.sagebionetworks.bridge.sdk.rest.models.participants;

import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sagebionetworks.bridge.sdk.rest.AuthenticationHandler;
import org.sagebionetworks.bridge.sdk.rest.AuthenticationService;
import org.sagebionetworks.bridge.sdk.rest.UploadService;
import org.sagebionetworks.bridge.sdk.rest.models.uploads.UploadRequest;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentials;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by liujoshua on 4/8/16.
 */
public class AuthenticationHandlerTest {
    private AuthenticationHandler authenticationHandler;
    private Retrofit bridgeAuthenticatedRetrofit;

    @Before
    public void before() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://webservices-develop.sagebridge.org")
                .addConverterFactory(GsonConverterFactory.create()).build();

        StudyUserCredentials credentials = new StudyUserCredentials("api", "bridgeit@sagebase.org", "a6rutHebadeX7Ch");
        AuthenticationService authenticationService = retrofit.create(AuthenticationService.class);

        authenticationHandler = new AuthenticationHandler(credentials, authenticationService);

        // AuthenticationHandler auth must be registered as both an interceptor and an authenticator
        OkHttpClient authedHttpClient =
                new OkHttpClient.Builder().authenticator(authenticationHandler).addInterceptor(authenticationHandler).build();

        bridgeAuthenticatedRetrofit = new Retrofit.Builder().baseUrl("https://webservices-develop.sagebridge.org").client(authedHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Test
    public void testReauthOnLogout() throws Exception {
        UploadRequest dummyUploadRequest = new UploadRequest(null, 0, null, null);

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
