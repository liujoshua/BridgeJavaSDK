package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.models.uploads.UploadRequest;
import org.sagebionetworks.bridge.sdk.models.uploads.UploadSession;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 4/8/16.
 */
public interface ParticipantService
{

    @Headers("Content-Type: application/json")
    @POST("v3/uploads")
    Call <UploadSession> requestUploadSession(@Body UploadRequest body);
}

