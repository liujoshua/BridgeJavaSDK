package org.sagebionetworks.bridge.sdk.rest;

import okhttp3.ResponseBody;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUser;
import org.sagebionetworks.bridge.sdk.rest.models.users.Session;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentials;
import org.sagebionetworks.bridge.sdk.rest.models.users.SignUpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 4/8/16.
 */
public interface AuthenticationService {
    @Headers("Content-Type: application/json")
    @POST("v3/auth/signIn")
    Call<Session> signIn(@Body StudyUserCredentials body);

    @POST("/v3/auth/signOut")
    Call<ResponseBody> signOut();

    @POST("/v3/auth/signUp")
    Call<ResponseBody> signUp(@Body SignUpRequest signUp);

    @POST("v3/auth/resendEmailVerification")
    Call<ResponseBody> resendEmailVerificataion(@Body StudyUser studyUser);

    @POST("/v3/auth/requestResetPassword")
    Call<ResponseBody> requestResetPassword(@Body StudyUser studyUser);
}

