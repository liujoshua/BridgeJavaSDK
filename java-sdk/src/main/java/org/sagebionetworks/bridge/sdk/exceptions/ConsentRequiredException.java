package org.sagebionetworks.bridge.sdk.exceptions;

import org.sagebionetworks.bridge.sdk.BridgeSession;

@SuppressWarnings("serial")
public final class ConsentRequiredException extends BridgeSDKException {

    private final BridgeSession session;

    public ConsentRequiredException(String message, String endpoint, BridgeSession session) {
        super(message, 412, endpoint);
        this.session = session;
    }

    public final BridgeSession getSession() {
        return session;
    }

    @Override
    public String toString() {
        return String.format("ConsentRequiredException[message=%s, statusCode=%s, endpoint=%s]", 
            getMessage(), getStatusCode(), getRestEndpoint());
    }
}
