package org.sagebionetworks.bridge.sdk.rest.models.subpopulation;

import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.ConsentStatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liujoshua on 6/15/16.
 */
public class ConsentStatusMixin extends ConsentStatus {
    @JsonCreator
    public ConsentStatusMixin(@JsonProperty("name") String name, @JsonProperty("subpopulationGuid") String subpopulationGuid,
                         @JsonProperty("required") boolean isRequired, @JsonProperty("consented") boolean isConsented,
                         @JsonProperty("mostRecentConsent") boolean isMostRecentConsent) {
        super(name, subpopulationGuid, isRequired, isConsented, isMostRecentConsent);
    }
}
