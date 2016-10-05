package org.sagebionetworks.bridge.sdk.models.accounts;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public final class StudyUserCredentials {

    private String studyIdentifier;
    private String email;
    private String password;

    @JsonCreator
    public StudyUserCredentials(@JsonProperty("study") String studyIdentifier, @JsonProperty("email") String email,
                                @JsonProperty("password") String password) {
        Preconditions.checkArgument(!Strings.nullToEmpty(studyIdentifier).trim().isEmpty(), "Study identifier cannot be blank/null");
        Preconditions.checkArgument(!Strings.nullToEmpty(email).trim().isEmpty(), "Email cannot be blank/null");
        Preconditions.checkArgument(!Strings.nullToEmpty(password).trim().isEmpty(), "Password cannot be blank/null");

        this.studyIdentifier = studyIdentifier;
        this.email = email;
        this.password = password;
    }

    /** @return the study the participant is signing in to */
    @JsonProperty("study")
    public String getStudyIdentifier() {
        return studyIdentifier;
    }
    
    /** @return the email of the participant */
    public String getEmail() {
        return this.email;
    }
    /**
     * NOTE: for migration support, this will be removed in early 2016.
     * @deprecated
     * @return the email of the participant 
     */
    public String getUsername() {
        return this.email;
    }
    /** @return the passwod of the participant */
    public String getPassword() {
        return this.password;
    }

    public void setStudyIdentifier(String studyIdentifier) {
        this.studyIdentifier = studyIdentifier;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(studyIdentifier);
        result = prime * result + Objects.hashCode(password);
        result = prime * result + Objects.hashCode(email);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudyUserCredentials other = (StudyUserCredentials) obj;
        return (Objects.equals(studyIdentifier, other.studyIdentifier) && Objects.equals(password, other.password) && Objects
                .equals(email, other.email));
    }

    @Override
    public String toString() {
        return String.format("StudyUserCredentials[study=%, email=%s, password=[REDACTED]", studyIdentifier, email);
    }
}
