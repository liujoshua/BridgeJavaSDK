package org.sagebionetworks.bridge.sdk.rest.models.users;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.Roles;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.AccountStatus;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.SharingScope;
import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;

/**
 * Created by liujoshua on 5/11/16.
 */
public class SignUpRequest extends StudyParticipant
{
    /**
     * The identifier for the study under which the user is signing in
     */
    private final String study;

    public SignUpRequest(String studyId,
                         String firstName,
                         String lastName,
                         String email,
                         String externalId,
                         String password,
                         SharingScope sharingScope,
                         Boolean notifyByEmail,
                         Set<String> dataGroups,
                         String healthCode,
                         Map<String, String> attributes,
                         Map<String, List<StudyUserConsentHistory>> consentHistories,
                         Set<Roles> roles,
                         DateTime createdOn,
                         AccountStatus status,
                         LinkedHashSet<String> languages,
                         String id) {
        super(
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
        this.study = studyId;
    }

    public String getStudy() {
        return study;
    }
}