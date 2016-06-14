package org.sagebionetworks.bridge.sdk.rest.models;

/**
 * Created by liujoshua on 6/8/16.
 */
public class MessageResponse {
    private final String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
