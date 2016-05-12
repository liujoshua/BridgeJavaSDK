package org.sagebionetworks.bridge.sdk.models.users;

import java.util.Objects;

public final class SignInBody {

    public final String study;
    public final String email;
    public final String password;


    public SignInBody(String study, String email, String password) {
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
        SignInBody other = (SignInBody) obj;
        return (Objects.equals(study, other.study) && Objects.equals(password, other.password) && Objects.equals(email, other.email));
    }

    @Override
    public String toString() {
        return String.format("SignInCredentials[study=%, email=%s, password=[REDACTED]", study, email);
    }
}
