package org.sagebionetworks.bridge.rest.models.users;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.AccountStatus;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.Roles;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.SharingScope;
import org.sagebionetworks.bridge.sdk.rest.models.users.SignUpRequest;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserConsentHistory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liujoshua on 6/15/16.
 */
public class SignUpRequestMixin extends SignUpRequest {
    @JsonCreator
    public SignUpRequestMixin(@JsonProperty("studyId") String studyId,
                              @JsonProperty("firstName") String firstName,
                              @JsonProperty("lastName") String lastName,
                              @JsonProperty("email") String email,
                              @JsonProperty("externalId") String externalId,
                              @JsonProperty("password") String password,
                              @JsonProperty("sharingScope") SharingScope sharingScope,
                              @JsonProperty("notifyByEmail") Boolean notifyByEmail,
                              @JsonProperty("dataGroups") Set<String> dataGroups,
                              @JsonProperty("healthCode") String healthCode,
                              @JsonProperty("attributes") Map<String, String> attributes,
                              @JsonProperty("consentHistories") Map<String, List<StudyUserConsentHistory>> consentHistories,
                              @JsonProperty("roles") Set<Roles> roles,
                              @JsonProperty("createdOn") DateTime createdOn,
                              @JsonProperty("status") AccountStatus status,
                              @JsonProperty("languages") LinkedHashSet<String> languages,
                              @JsonProperty("id") String id) {
        super(
                studyId,
                firstName,
                lastName,
                email,
                externalId,
                password,
                sharingScope,
                notifyByEmail,
                dataGroups,
                healthCode,
                attributes,
                consentHistories,
                roles,
                createdOn,
                status,
                languages,
                id
        );
    }
}
