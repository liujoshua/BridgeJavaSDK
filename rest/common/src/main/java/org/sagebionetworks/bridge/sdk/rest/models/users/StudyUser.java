package org.sagebionetworks.bridge.sdk.rest.models.users;

import java.util.Objects;

/**
 * Created by liujoshua on 5/11/16.
 */
public class StudyUser
{
    private final String study;
    private final String email;

    public StudyUser(String study, String email) {
        this.study = study;
        this.email = email;
    }

    public String getStudy() {
        return study;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(email);
        result = prime * result + Objects.hashCode(study);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudyUser other = (StudyUser) obj;
        return Objects.equals(email, other.email) && Objects.equals(study, other.study);
    }

    @Override
    public String toString() {
        return String.format("StudyUser [study=%s, email=%s]", study, email);
    }


}