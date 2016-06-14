package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.rest.models.MessageResponse;
import org.sagebionetworks.bridge.sdk.rest.models.consents.ChangeSharingScopeRequest;
import org.sagebionetworks.bridge.sdk.rest.models.consents.SubmitConsentRequest;
import org.sagebionetworks.bridge.sdk.rest.models.consents.WithdrawConsentRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by liujoshua on 6/8/16.
 */
public interface ConsentService {

    @POST("v3/users/self/dataSharing")
    Call<MessageResponse> changeSharingScope(@Body ChangeSharingScopeRequest body);

    @Headers("Content-Type: application/json")
    @POST("v3/subpopulations/{studyId}/consents/signature")
    Call<MessageResponse> submitConsent(@Path("studyId") String studyId, @Body SubmitConsentRequest body);


    @POST("v3/subpopulations/{studyId}/consents/signature/withdraw")
    Call<MessageResponse> withdrawConsent(@Path("studyId") String studyId, @Body WithdrawConsentRequest withdrawal);

}
