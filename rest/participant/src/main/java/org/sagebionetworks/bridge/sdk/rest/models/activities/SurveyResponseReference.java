package org.sagebionetworks.bridge.sdk.rest.models.activities;

import java.util.Objects;

/**
 * Created by liujoshua on 6/13/16.
 */
public class SurveyResponseReference {
    private final String identifier;
    private final String href;

    public SurveyResponseReference(String identifier, String href) {
        this.identifier = identifier;
        this.href = href;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getHref() {
        return href;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(identifier);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SurveyResponseReference other = (SurveyResponseReference) obj;
        return (Objects.equals(identifier, other.identifier));
    }

    @Override
    public String toString() {
        return "SurveyResponseReference [identifier=" + identifier + ", href=" + getHref() + "]";
    }
}
