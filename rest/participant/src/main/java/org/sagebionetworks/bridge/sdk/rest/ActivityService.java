package org.sagebionetworks.bridge.sdk.rest;

import java.util.List;

import org.joda.time.DateTimeZone;
import org.sagebionetworks.bridge.sdk.rest.models.MessageResponse;
import org.sagebionetworks.bridge.sdk.rest.models.PagedResourceList;
import org.sagebionetworks.bridge.sdk.rest.models.activities.ScheduledActivity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liujoshua on 6/10/16.
 */
public interface ActivityService {
    @GET("v3/activities")
    Call<PagedResourceList<ScheduledActivity>> getScheduledActivities(@Query("daysAhead") int daysAhead,
                                                                      @Query("offset") DateTimeZone timeZone);

    @POST("v3/activities")
    Call<MessageResponse> updateScheduledActivities(List<ScheduledActivity> scheduledActivities);
}
