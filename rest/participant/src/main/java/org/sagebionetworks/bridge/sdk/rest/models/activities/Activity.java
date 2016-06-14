package org.sagebionetworks.bridge.sdk.rest.models.activities;

import java.util.Objects;

/**
 * Created by liujoshua on 6/13/16.
 */
public class Activity {
    private final String label;
    private final String labelDetail;
    private final String guid;
    private final TaskReference task;
    private final SurveyReference survey;
    private final SurveyResponseReference response;
    private final ActivityType activityType;

    public Activity(String label,
                    String labelDetail,
                    String guid,
                    TaskReference task,
                    SurveyReference survey,
                    SurveyResponseReference response,
                    ActivityType activityType) {
        this.label = label;
        this.labelDetail = labelDetail;
        this.guid = guid;
        this.task = task;
        this.survey = survey;
        this.response = response;
        this.activityType = activityType;
    }

    /**
     * A label to show a participant in order to identify this activity in a user interface.
     */
    public String getLabel() {
        return label;
    }

    /**
     * A label detail to show a participant in order to identify this activity in a user interface.
     */
    public String getLabelDetail() {
        return labelDetail;
    }

    /**
     * The GUID for this activity.
     *
     * @return
     */
    public String getGuid() {
        return guid;
    }

    /**
     * The type of this activity.
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * For survey activities, the key object for the survey referenced by the activity. This can be used
     * through the SDK to retrieve the survey.
     */
    public SurveyReference getSurvey() {
        return survey;
    }

    /**
     * For task activities, the key object indicating the task to be performed.
     */
    public TaskReference getTask() {
        return task;
    }

    /**
     * For survey activities, the key object for the survey response referenced by the activity. This can be used
     * through the SDK to submit survey answers and retrieve saved answers.
     */
    public SurveyResponseReference getSurveyResponse() {
        return response;
    }

    public boolean isPersistentlyRescheduledBy(Schedule schedule) {
        return schedule.schedulesImmediatelyAfterEvent() && getActivityFinishedEventId(schedule);
    }

    private boolean getActivityFinishedEventId(Schedule schedule) {
        String activityFinishedEventId = "activity:" + getGuid() + ":finished";
        return schedule.getEventId().contains(getSelfFinishedEventId()) || schedule.getEventId().contains(activityFinishedEventId);
    }

    private String getSelfFinishedEventId() {
        return (getActivityType() == ActivityType.SURVEY) ? ("survey:" + getSurvey().getGuid() + ":finished") : ("task:" +
                getTask().getIdentifier() + ":finished");
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityType, label, labelDetail, response, survey, guid, task);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Activity other = (Activity) obj;
        return (Objects.equals(activityType, other.activityType) &&
                Objects.equals(label, other.label) && Objects.equals(labelDetail, other.labelDetail) &&
                Objects.equals(response, other.response) && Objects.equals(survey, other.survey) &&
                Objects.equals(guid, other.guid) && Objects.equals(task, other.task));

    }

    @Override
    public String toString() {
        return String.format(
                "Activity [label=%s, labelDetail=%s, guid=%s,task=%s, survey=%s, response=%s, activityType=%s]",
                label,
                labelDetail,
                guid,
                task,
                survey,
                response,
                activityType
        );
    }
}
