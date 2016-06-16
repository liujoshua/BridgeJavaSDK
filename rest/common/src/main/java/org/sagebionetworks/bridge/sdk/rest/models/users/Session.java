package org.sagebionetworks.bridge.sdk.rest.models.users;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.ConsentStatus;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

/**
 * Created by liujoshua on 5/11/16.
 */
public class Session {

    private final String sessionToken;
    private final boolean authenticated;
    private final Map<SubpopulationGuid,ConsentStatus> consentStatuses;
    private final StudyParticipant studyParticipant;

    public Session(String sessionToken, boolean authenticated, Map<SubpopulationGuid, ConsentStatus> consentStatuses,
                StudyParticipant studyParticipant) {
        this.sessionToken = sessionToken;
        this.authenticated = authenticated;
        this.studyParticipant = studyParticipant;
        this.consentStatuses = (consentStatuses == null) ? Collections.unmodifiableMap(new HashMap()) :
                Collections.unmodifiableMap(consentStatuses);
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public boolean isConsented() {
        return ConsentStatus.isUserConsented(consentStatuses);
    }

    public StudyParticipant getStudyParticipant() {
        return this.studyParticipant;
    }

    public boolean hasSignedMostRecentConsent() {
        return ConsentStatus.isConsentCurrent(consentStatuses);
    }
}
