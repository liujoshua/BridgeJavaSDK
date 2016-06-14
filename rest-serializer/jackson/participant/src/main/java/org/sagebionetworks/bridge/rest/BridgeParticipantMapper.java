package org.sagebionetworks.bridge.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by liujoshua on 6/13/16.
 */
public class BridgeParticipantMapper extends BridgeCommonMapper {
    public ObjectMapper getMapper() {
        ObjectMapper m = super.getMapper();
        m.registerModule(new BridgeParticipantModule());
        return m;
    }
}
