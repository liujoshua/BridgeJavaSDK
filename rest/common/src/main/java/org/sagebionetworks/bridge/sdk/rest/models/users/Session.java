package org.sagebionetworks.bridge.sdk.rest.models.users;

import java.util.Map;

import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

/**
 * Created by liujoshua on 5/11/16.
 */
public class Session {
    private static final TypeReference<Map<SubpopulationGuid, ConsentStatus>> CONSENT_STATUS_MAP = new TypeReference<Map<SubpopulationGuid, ConsentStatus>>() {};
    private static final ObjectMapper MAPPER = Utilities.getMapper();

    @JsonCreator
    public static final UserSession fromJSON(JsonNode node) {
        try {
            String sessionToken = node.get("sessionToken").asText();
            boolean authenticated = node.get("authenticated").asBoolean();
            Map<SubpopulationGuid, ConsentStatus> consentStatuses = MAPPER.convertValue(
                    node.get("consentStatuses"), CONSENT_STATUS_MAP);
            StudyParticipant studyParticipant = MAPPER.treeToValue(node, StudyParticipant.class);
            return new UserSession(sessionToken, authenticated, consentStatuses, studyParticipant);
        } catch(JsonProcessingException e) {
            throw new BridgeSDKException(e.getMessage(), e);
        }
    }


    private final String sessionToken;
    private final boolean authenticated;
    @JsonDeserialize(keyUsing = SubpopulationGuidKeyDeserializer.class)
    private final Map<SubpopulationGuid,ConsentStatus> consentStatuses;
    private final StudyParticipant studyParticipant;

    UserSession(String sessionToken, boolean authenticated, Map<SubpopulationGuid, ConsentStatus> consentStatuses,
                StudyParticipant studyParticipant) {
        this.sessionToken = sessionToken;
        this.authenticated = authenticated;
        this.studyParticipant = studyParticipant;
        this.consentStatuses = (consentStatuses == null) ? ImmutableMap.<SubpopulationGuid,ConsentStatus>of() :
                ImmutableMap.copyOf(consentStatuses);
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
