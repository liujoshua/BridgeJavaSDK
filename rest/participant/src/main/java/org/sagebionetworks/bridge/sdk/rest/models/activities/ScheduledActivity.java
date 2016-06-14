package org.sagebionetworks.bridge.sdk.rest.models.activities;

import java.util.Objects;

import org.joda.time.DateTime;

/**
 * Created by liujoshua on 6/13/16.
 */
public class ScheduledActivity {
    private final String guid;
    private final Activity activity;
    private final DateTime scheduledOn;
    private final DateTime expiresOn;
    private DateTime startedOn;
    private DateTime finishedOn;
    private final Integer minAppVersion;
    private final Integer maxAppVersion;
    private boolean persistent;

    public ScheduledActivity(String guid,
                             Activity activity,
                             DateTime scheduledOn,
                             DateTime expiresOn,
                             DateTime startedOn,
                             DateTime finishedOn,
                             Integer minAppVersion,
                             Integer maxAppVersion,
                             boolean persistent) {
        this.guid = guid;
        this.activity = activity;
        this.scheduledOn = scheduledOn;
        this.expiresOn = expiresOn;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.minAppVersion = minAppVersion;
        this.maxAppVersion = maxAppVersion;
        this.persistent = persistent;
    }

    public ScheduledActivityStatus getStatus() {
        if (finishedOn != null && startedOn == null) {
            return ScheduledActivityStatus.DELETED;
        } else if (finishedOn != null && startedOn != null) {
            return ScheduledActivityStatus.FINISHED;
        } else if (startedOn != null) {
            return ScheduledActivityStatus.STARTED;
        } else if (expiresOn != null && DateTime.now().isAfter(expiresOn)) {
            return ScheduledActivityStatus.EXPIRED;
        } else if (scheduledOn != null && DateTime.now().isBefore(scheduledOn)) {
            return ScheduledActivityStatus.SCHEDULED;
        }
        return ScheduledActivityStatus.AVAILABLE;
    }

    public String getGuid() {
        return guid;
    }

    public Activity getActivity() {
        return activity;
    }

    public DateTime getScheduledOn() {
        return scheduledOn;
    }

    public DateTime getExpiresOn() {
        return expiresOn;
    }

    public DateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(DateTime startedOn) {
        this.startedOn = startedOn;
    }

    public DateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(DateTime finishedOn) {
        this.finishedOn = finishedOn;
    }

    public Integer getMinAppVersion() {
        return minAppVersion;
    }

    public Integer getMaxAppVersion() {
        return maxAppVersion;
    }

    public boolean getPersistent() {
        return persistent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activity, expiresOn, finishedOn, guid, scheduledOn, startedOn, minAppVersion, maxAppVersion, persistent);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ScheduledActivity other = (ScheduledActivity) obj;
        return (Objects.equals(activity, other.activity) && Objects.equals(expiresOn, other.expiresOn) &&
                Objects.equals(finishedOn, other.finishedOn) && Objects.equals(guid, other.guid) &&
                Objects.equals(scheduledOn, other.scheduledOn) && Objects.equals(startedOn, other.startedOn) &&
                Objects.equals(persistent, other.persistent) && Objects.equals(minAppVersion, other.minAppVersion) &&
                Objects.equals(maxAppVersion, other.maxAppVersion));
    }

    @Override
    public String toString() {
        return String.format(
                "ScheduledActivity [guid=%s, activity=%s, scheduledOn=%s, expiresOn=%s, startedOn=%s, finishedOn=%s, persistent=%s, " +
                        "minAppVersion=%s, maxAppVersion=%s]",
                guid,
                activity,
                scheduledOn,
                expiresOn,
                startedOn,
                finishedOn,
                persistent,
                minAppVersion,
                maxAppVersion
        );
    }
}

