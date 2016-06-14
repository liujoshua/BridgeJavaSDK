package org.sagebionetworks.bridge.sdk.rest;

import okhttp3.Response;
import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by liujoshua on 6/8/16.
 */
public interface ParticipantService {
    @GET("v3/participants/self")
    Call<StudyParticipant> getParticipant();

    @POST("v3/participants/self")
    Call<Response> saveParticipant(@Body StudyParticipant participant);
}
