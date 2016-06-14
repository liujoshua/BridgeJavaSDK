package org.sagebionetworks.bridge.sdk.rest.models.users;

/**
 * Created by liujoshua on 5/11/16.
 */
public class Session {
    private final String sessionToken;

    public Session(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }
}
