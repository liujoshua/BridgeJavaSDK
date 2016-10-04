package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.Session;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyParticipant;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyUserCredentials;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 10/4/16.
 */
public interface AuthenticationService {
    @POST("/v3/auth/signUp")
    Call<ResponseBody> signUp(@Body StudyParticipant participant);

    @Headers("Content-Type: application/json")
    @POST("v3/auth/signIn")
    Call<Session> signIn(@Body StudyUserCredentials body);

    @POST("/v3/auth/signOut")
    Call<ResponseBody> signOut();

    @POST("v3/auth/resendEmailVerification")
    Call<ResponseBody> resendEmailVerification(@Body StudyUserCredentials studyUser);

    @POST("/v3/auth/requestResetPassword")
    Call<ResponseBody> requestResetPassword(@Body StudyUserCredentials studyUser);
}
