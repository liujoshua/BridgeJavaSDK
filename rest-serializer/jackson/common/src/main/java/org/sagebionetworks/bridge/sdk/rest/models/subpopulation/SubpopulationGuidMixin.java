package org.sagebionetworks.bridge.sdk.rest.models.subpopulation;

import com.fasterxml.jackson.annotation.JsonValue;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

/**
 * Created by liujoshua on 6/10/16.
 */
public abstract class SubpopulationGuidMixin extends SubpopulationGuid {
    public SubpopulationGuidMixin(String guid) {
        super(guid);
    }

    @Override
    @JsonValue
    public abstract String getGuid();
}
