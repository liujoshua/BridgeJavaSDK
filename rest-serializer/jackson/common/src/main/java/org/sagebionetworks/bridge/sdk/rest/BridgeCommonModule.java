package org.sagebionetworks.bridge.sdk.rest;

import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipantMixin;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulation.ConsentStatusMixin;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulation.SubpopulationGuidMixin;
import org.sagebionetworks.bridge.sdk.rest.models.users.SessionMixin;
import org.sagebionetworks.bridge.sdk.rest.models.users.SignUpRequestMixin;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserConsentHistoryMixin;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentialsMixin;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserMixin;
import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.ConsentStatus;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;
import org.sagebionetworks.bridge.sdk.rest.models.users.Session;
import org.sagebionetworks.bridge.sdk.rest.models.users.SignUpRequest;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUser;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserConsentHistory;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentials;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by liujoshua on 6/10/16.
 */
public class BridgeCommonModule extends SimpleModule {
    public BridgeCommonModule() {
        super();
        setMixInAnnotation(StudyParticipant.class, StudyParticipantMixin.class);
        setMixInAnnotation(ConsentStatus.class, ConsentStatusMixin.class);
        setMixInAnnotation(SubpopulationGuid.class, SubpopulationGuidMixin.class);
        setMixInAnnotation(Session.class, SessionMixin.class);
        setMixInAnnotation(SignUpRequest.class, SignUpRequestMixin.class);
        setMixInAnnotation(StudyUserConsentHistory.class, StudyUserConsentHistoryMixin.class);
        setMixInAnnotation(StudyUserCredentials.class, StudyUserCredentialsMixin.class);
        setMixInAnnotation(StudyUser.class, StudyUserMixin.class);
    }
}
