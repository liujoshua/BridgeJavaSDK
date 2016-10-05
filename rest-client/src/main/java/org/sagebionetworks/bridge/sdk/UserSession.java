package org.sagebionetworks.bridge.sdk;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import org.sagebionetworks.bridge.sdk.exceptions.BridgeSDKException;
import org.sagebionetworks.bridge.sdk.json.SubpopulationGuidKeyDeserializer;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyParticipant;
import org.sagebionetworks.bridge.sdk.models.subpopulations.ConsentStatus;
import org.sagebionetworks.bridge.sdk.models.subpopulations.SubpopulationGuid;


public final class UserSession {

    private static final TypeReference<Map<SubpopulationGuid, ConsentStatus>> CONSENT_STATUS_MAP = new TypeReference<Map<SubpopulationGuid, ConsentStatus>>() {};
    private static final ObjectMapper MAPPER = Configuration.getMapper();
    
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
    
    public Map<SubpopulationGuid,ConsentStatus> getConsentStatuses() { 
        return consentStatuses;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sessionToken", sessionToken)
                .add("authenticated", authenticated)
                .add("consented", isConsented())
                .add("signedMostRecentConsent", hasSignedMostRecentConsent())
                .add("studyParticipant", studyParticipant)
                .add("consentStatuses", consentStatuses)
                .toString();
    }

}
