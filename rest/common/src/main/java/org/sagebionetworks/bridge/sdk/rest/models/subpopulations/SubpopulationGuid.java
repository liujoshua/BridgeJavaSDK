package org.sagebionetworks.bridge.sdk.rest.models.subpopulations;

/**
 * Created by liujoshua on 6/8/16.
 */

import java.util.Objects;

public class SubpopulationGuid {
    private final String guid;

    public SubpopulationGuid(String guid) {
        Objects.requireNonNull(guid);
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }

    public int hashCode() {
        return Objects.hash(guid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SubpopulationGuid other = (SubpopulationGuid) obj;
        return Objects.equals(guid, other.guid);
    }

    @Override
    public String toString() {
        return guid;
    }
}
