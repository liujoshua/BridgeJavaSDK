package org.sagebionetworks.bridge.sdk.rest;


import org.sagebionetworks.bridge.sdk.rest.models.MessageResponse;
import org.sagebionetworks.bridge.sdk.rest.models.uploads.UploadRequest;
import org.sagebionetworks.bridge.sdk.rest.models.uploads.UploadSession;
import org.sagebionetworks.bridge.sdk.rest.models.uploads.UploadStatusResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by liujoshua on 4/8/16.
 */
public interface UploadService
{

    @Headers("Content-Type: application/json")
    @POST("v3/uploads")
    Call<UploadSession> requestUploadSession(@Body UploadRequest body);

    @POST("v3/uploads/{id}/complete")
    Call<MessageResponse> uploadComplete(@Path("id") String id);

    @GET("v3/uploadstatuses/{id}")
    Call<UploadStatusResponse> uploadStatus(@Path("id") String id);
}

