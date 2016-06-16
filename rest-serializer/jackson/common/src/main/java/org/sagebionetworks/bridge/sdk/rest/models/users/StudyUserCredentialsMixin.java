package org.sagebionetworks.bridge.sdk.rest.models.users;

import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentials;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liujoshua on 6/15/16.
 */
public class StudyUserCredentialsMixin extends StudyUserCredentials {
    public StudyUserCredentialsMixin(@JsonProperty("study") String studyIdentifier,
                                     @JsonProperty("email") String email,
                                     @JsonProperty("password") String password) {
        super(studyIdentifier, email, password);
    }
}
