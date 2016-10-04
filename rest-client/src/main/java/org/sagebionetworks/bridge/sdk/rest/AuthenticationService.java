package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.Session;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyUserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 10/4/16.
 */
public interface AuthenticationService {
    @Headers("Content-Type: application/json")
    @POST("v3/auth/signIn")
    Call<Session> signIn(@Body StudyUserCredentials body);
}
