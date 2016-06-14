package org.sagebionetworks.bridge.sdk.rest.models.activities;

import java.util.Objects;

/**
 * Created by liujoshua on 6/13/16.
 */
public class TaskReference {
    private final String identifier;

    public TaskReference(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TaskReference other = (TaskReference) obj;
        return (Objects.equals(identifier, other.identifier));
    }

    @Override
    public String toString() {
        return "TaskReference [identifier=" + identifier + "]";
    }
}
