package org.sagebionetworks.bridge.sdk.rest.models.users;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liujoshua on 6/15/16.
 */
public class StudyUserConsentHistoryMixin extends StudyUserConsentHistory {
    @JsonCreator
    StudyUserConsentHistoryMixin(@JsonProperty("subpopulationGuid") SubpopulationGuid subpopGuid,
                                 @JsonProperty("consentCreatedOn") DateTime consentCreatedOn,
                                 @JsonProperty("name") String name,
                                 @JsonProperty("birthdate") LocalDate birthdate,
                                 @JsonProperty("imageData") String imageData,
                                 @JsonProperty("imageMimeType") String imageMimeType,
                                 @JsonProperty("signedOn") DateTime signedOn,
                                 @JsonProperty("withdrewOn") DateTime withdrewOn,
                                 @JsonProperty("hasSignedActiveConsent") boolean hasSignedActiveConsent) {
        super(subpopGuid, consentCreatedOn, name, birthdate, imageData, imageMimeType, signedOn, withdrewOn, hasSignedActiveConsent);
    }
}
