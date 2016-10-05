package org.sagebionetworks.bridge.sdk.models.holders;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

public final class SimpleGuidHolder implements GuidHolder {

    private final String guid;
    
    @JsonCreator
    public SimpleGuidHolder(@JsonProperty("guid") String guid) {
        checkNotNull(!Strings.nullToEmpty(guid).trim().isEmpty(), "%s cannot be blank", "guid");
        this.guid = guid;
    }
    
    @Override
    public String getGuid() {
        return guid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(guid);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SimpleGuidHolder other = (SimpleGuidHolder) obj;
        return Objects.equals(guid, other.guid);
    }

    @Override
    public String toString() {
        return String.format("SimpleGuidHolder [guid=%s]", guid);
    }

}
