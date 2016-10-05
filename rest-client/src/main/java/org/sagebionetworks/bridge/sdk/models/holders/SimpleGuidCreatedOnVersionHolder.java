package org.sagebionetworks.bridge.sdk.models.holders;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


import java.util.Objects;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

public final class SimpleGuidCreatedOnVersionHolder implements GuidCreatedOnVersionHolder {

    private final String guid;
    private final DateTime createdOn;
    private final Long version;

    @JsonCreator
    public SimpleGuidCreatedOnVersionHolder(@JsonProperty("guid") String guid,
            @JsonProperty("createdOn") DateTime createdOn, @JsonProperty("version") Long version) {
        checkArgument(!Strings.nullToEmpty(guid).trim().isEmpty(), "%s cannot be blank", "guid");
        checkNotNull(createdOn, "%s cannot be null", "createdOn");
        
        this.guid = guid;
        this.createdOn = createdOn;
        this.version = version;
    }

    /**
     * Copy constructor. This is used to get an immutable simple version holder from a survey or 
     * an unknown concrete version holder type.
     * @param other
     *      the holder object to copy
     */
    public SimpleGuidCreatedOnVersionHolder(GuidCreatedOnVersionHolder other) {
        this(other.getGuid(), other.getCreatedOn(), other.getVersion());
    }
    
    /** @return the GUID of the model object */
    public String getGuid() {
        return guid;
    }

    /** @return the createdOn timestamp of the revision of the model object */
    public DateTime getCreatedOn() {
        return createdOn;
    }
    
    /** @return the version of the model object. This is an optimistic locking version and although 
     * it is needed for update operations, it is not necessary to supply it for retrieving or 
     * creating model objects.      
     */
    public Long getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(createdOn);
        result = prime * result + Objects.hashCode(guid);
        result = prime * result + Objects.hashCode(version);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SimpleGuidCreatedOnVersionHolder other = (SimpleGuidCreatedOnVersionHolder) obj;
        return (Objects.equals(createdOn, other.createdOn) && Objects.equals(guid, other.guid) && Objects.equals(
                version, other.version));
    }

    @Override
    public String toString() {
        return String.format("SimpleGuidCreatedOnVersionHolder [guid=%s, createdOn=%s, version=%s]",
                guid, createdOn, version);
    }
}
