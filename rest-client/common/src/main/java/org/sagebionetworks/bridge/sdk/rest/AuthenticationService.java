package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.models.users.EmailBody;
import org.sagebionetworks.bridge.sdk.models.users.SignInBody;
import org.sagebionetworks.bridge.sdk.models.users.SignUpBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 4/8/16.
 */
public interface AuthenticationService {

    //    v3.auth.signin = /v3/auth/signIn
    //    v3.auth.signout = /v3/auth/signOut
    //    v3.auth.requestresetpassword = /v3/auth/requestResetPassword
    //    v3.auth.resetpassword = /v3/auth/resetPassword
    //    v3.auth.signup = /v3/auth/signUp
    //    v3.auth.verifyemail = /v3/auth/verifyEmail
    //    v3.auth.resendemailverification = /v3/auth/resendEmailVerification

    /**
     * @return One of the following responses
     * <ul>
     * <li><b>200</b> returns UserSessionInfo Object</li>
     * <li><b>404</b> error - "Credentials incorrect or missing"</li>
     * <li><b>412</b> error - "User has not consented to research"</li>
     * </ul>
     */
    @Headers("Content-Type: application/json")
    @POST("v3/auth/signIn")
    Call<Session> signIn(@Body SignInBody body);

    @POST("/v3/auth/signOut")
    Call<Session> signOut();

    @POST("/v3/auth/signUp")
    Call<Session> signUp(@Body SignUpBody signUp);

    @POST("/v3/auth/verifyEmail")
    Call<Session> verifyEmail();

    @POST("v3/auth/resendEmailVerification")
    Call<Session> resendEmailVerificataion(@Body EmailBody emailVerification);

    @POST("/v3/auth/requestResetPassword")
    Call<Session> requestResetPassword();

    @POST("v3/auth/resetPassword")
    Call<Session> resetPassword();
}

