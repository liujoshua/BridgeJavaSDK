package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.models.upload.UploadRequest;
import org.sagebionetworks.bridge.sdk.models.upload.UploadSession;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 10/4/16.
 */
public interface UploadService {
    @Headers("Content-Type: application/json")
    @POST("v3/uploads")
    Call<UploadSession> requestUploadSession(@Body UploadRequest body);
}
