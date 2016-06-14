package org.sagebionetworks.bridge.sdk.rest.models.activities;

import java.util.Objects;

import org.joda.time.DateTime;

/**
 * Created by liujoshua on 6/13/16.
 */
public class SurveyReference {
    private final String guid;
    private final DateTime createdOn;
    private final String href;

    public String getGuid() {
        return guid;
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }

    public String getHref() {
        return href;
    }

    public SurveyReference(String guid, DateTime createdOn, String href) {
        this.guid = guid;
        this.createdOn = createdOn;

        this.href = href;
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, createdOn, href);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SurveyReference other = (SurveyReference) obj;
        return (Objects.equals(createdOn, other.createdOn) && Objects.equals(guid, other.guid));
    }

    @Override
    public String toString() {
        return String.format("SurveyReference [guid=%s, createdOn=%s, href=%s]", guid, createdOn, href);
    }
}
