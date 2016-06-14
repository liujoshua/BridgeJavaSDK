package org.sagebionetworks.bridge.rest;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.sagebionetworks.bridge.rest.models.participants.StudyParticipantMixin;
import org.sagebionetworks.bridge.rest.models.subpopulation.SubpopulationGuidMixin;
import org.sagebionetworks.bridge.sdk.rest.models.participants.StudyParticipant;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

/**
 * Created by liujoshua on 6/10/16.
 */
public class BridgeCommonModule extends SimpleModule {
    public BridgeCommonModule() {
        super();
        setMixInAnnotation(StudyParticipant.class, StudyParticipantMixin.class);
        setMixInAnnotation(SubpopulationGuid.class, SubpopulationGuidMixin.class);
    }
}
