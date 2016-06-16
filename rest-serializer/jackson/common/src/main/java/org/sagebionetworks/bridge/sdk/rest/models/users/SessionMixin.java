package org.sagebionetworks.bridge.sdk.rest.models.users;

import java.io.IOException;
import java.util.Map;

import org.sagebionetworks.bridge.sdk.rest.BridgeCommonMapper;
import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.ConsentStatus;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by liujoshua on 6/15/16.
 */
@JsonSerialize(using = SessionMixin.SessionSerializer.class)
@JsonDeserialize(using = SessionMixin.SessionDeserializer.class)
public class SessionMixin extends Session {
    static class SessionDeserializer extends JsonDeserializer<Session> {

        @Override
        public Session deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return null;
        }
    }

    static class SessionSerializer extends JsonSerializer<Session> {

        @Override
        public void serialize(Session value,
                              JsonGenerator gen,
                              SerializerProvider serializers) throws IOException, JsonProcessingException {

        }

    }

    private static final TypeReference<Map<SubpopulationGuid, ConsentStatus>> CONSENT_STATUS_MAP =
            new TypeReference<Map<SubpopulationGuid, ConsentStatus>>() {};
    private static final ObjectMapper MAPPER = new BridgeCommonMapper().getMapper();

    SessionMixin(String sessionToken,
                 boolean authenticated,
                 Map<SubpopulationGuid, ConsentStatus> consentStatuses,
                 StudyParticipant studyParticipant) {
        super(sessionToken, authenticated, consentStatuses, studyParticipant);
    }

    @JsonCreator
    public static Session fromJSON(JsonNode node) throws JsonProcessingException {
        String sessionToken = node.get("sessionToken").asText();
        boolean authenticated = node.get("authenticated").asBoolean();
        Map<SubpopulationGuid, ConsentStatus> consentStatuses = MAPPER.convertValue(node.get("consentStatuses"), CONSENT_STATUS_MAP);
        StudyParticipant studyParticipant = MAPPER.treeToValue(node, StudyParticipant.class);
        return new Session(sessionToken, authenticated, consentStatuses, studyParticipant);
    }

}
