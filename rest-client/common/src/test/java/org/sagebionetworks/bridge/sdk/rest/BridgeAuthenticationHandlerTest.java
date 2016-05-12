package org.sagebionetworks.bridge.sdk.rest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.sagebionetworks.bridge.sdk.models.uploads.UploadRequest;
import org.sagebionetworks.bridge.sdk.models.users.SignInBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by liujoshua on 4/8/16.
 */
public class BridgeAuthenticationHandlerTest {
    private BridgeAuthenticationHandler bridgeAuthenticationHandler;
    private Retrofit bridgeAuthenticatedRetrofit;

    @Before
    public void before() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://webservices-develop.sagebridge.org")
                .addConverterFactory(GsonConverterFactory.create()).build();

        SignInBody credentials = new SignInBody("api", "bridgeit@sagebase.org", "a6rutHebadeX7Ch");
        AuthenticationService authenticationService = retrofit.create(AuthenticationService.class);

        bridgeAuthenticationHandler = new BridgeAuthenticationHandler(credentials, authenticationService);

        // BridgeAuthenticationHandler auth must be registered as both an interceptor and an authenticator
        OkHttpClient authedHttpClient =
                new OkHttpClient.Builder().authenticator(bridgeAuthenticationHandler).addInterceptor(bridgeAuthenticationHandler).build();

        bridgeAuthenticatedRetrofit = new Retrofit.Builder().baseUrl("https://webservices-develop.sagebridge.org").client(authedHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Test
    public void testReauthOnLogout() throws Exception {
        UploadRequest dummyUploadRequest = new UploadRequest(null, 0, null, null);

        ParticipantService svc = bridgeAuthenticatedRetrofit.create(ParticipantService.class);
        svc.requestUploadSession(dummyUploadRequest).execute();

        String authToken = bridgeAuthenticationHandler.getSessionToken();
        assertNotNull(authToken);
        assertTrue(authToken.length() > 0);

        // expires token
        AuthenticationService authenticatedAuthService = bridgeAuthenticatedRetrofit.create(AuthenticationService.class);
        authenticatedAuthService.signOut().execute();

        svc.requestUploadSession(dummyUploadRequest).execute();

        // verify new session token is now being used
        String newAuthToken = bridgeAuthenticationHandler.getSessionToken();
        assertNotNull(newAuthToken);
        assertTrue(newAuthToken.length() > 0);
        assertNotEquals(authToken, newAuthToken);
    }

}
