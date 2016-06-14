package org.sagebionetworks.bridge.sdk.rest.models.users;

import java.util.Objects;

public class StudyUserCredentials {

    public final String study;
    public final String email;
    public final String password;


    public StudyUserCredentials(String study, String email, String password) {
        this.study = Objects.requireNonNull(study);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(study, email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudyUserCredentials other = (StudyUserCredentials) obj;
        return (Objects.equals(study, other.study) && Objects.equals(password, other.password) && Objects.equals(email, other.email));
    }

    @Override
    public String toString() {
        return String.format("SignInCredentials[study=%, email=%s, password=[REDACTED]", study, email);
    }
}
