package org.sagebionetworks.bridge.sdk.rest.models.participants;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.joda.time.DateTime;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.Roles;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.AccountStatus;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.SharingScope;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserConsentHistory;

public class StudyParticipant {

    private final String firstName;
    private final String lastName;
    private final String externalId;
    private final String password;
    private final SharingScope sharingScope;
    private final Boolean notifyByEmail;
    private final String email;
    private final Set<String> dataGroups;
    private final String healthCode;
    private final Map<String, List<StudyUserConsentHistory>> consentHistories;
    private final Set<Roles> roles;
    private final LinkedHashSet<String> languages;
    private final DateTime createdOn;
    private final AccountStatus status;
    private final String id;
    private final Map<String, String> attributes;


    public StudyParticipant(String firstName,
                     String lastName,
                     String email,
                     String externalId,
                     String password,
                     SharingScope sharingScope,
                     Boolean notifyByEmail,
                     Set<String> dataGroups,
                     String healthCode,
                     Map<String, String> attributes,
                     Map<String, List<StudyUserConsentHistory>> consentHistories,
                     Set<Roles> roles,
                     DateTime createdOn,
                     AccountStatus status,
                     LinkedHashSet<String> languages,
                     String id) {
        LinkedHashSet<String> finalLangs = (languages == null) ? null : new LinkedHashSet<>(languages);
        this.firstName = firstName;
        this.lastName = lastName;
        this.externalId = externalId;
        this.password = password;
        this.sharingScope = sharingScope;
        this.notifyByEmail = notifyByEmail;
        this.email = email;
        this.dataGroups = dataGroups;
        this.healthCode = healthCode;
        this.consentHistories = consentHistories;
        this.roles = roles;
        this.createdOn = createdOn;
        this.status = status;
        this.id = id;
        this.languages = finalLangs;
        this.attributes = attributes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getPassword() {
        return password;
    }

    public SharingScope getSharingScope() {
        return sharingScope;
    }

    public Boolean isNotifyByEmail() {
        return notifyByEmail;
    }

    public Set<String> getDataGroups() {
        return dataGroups;
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public String getHealthCode() {
        return healthCode;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Map<String, List<StudyUserConsentHistory>> getConsentHistories() {
        return consentHistories;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public LinkedHashSet<String> getLanguages() {
        return languages;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes,
                consentHistories,
                dataGroups,
                email,
                externalId,
                password,
                firstName,
                lastName,
                healthCode,
                languages,
                notifyByEmail,
                roles,
                createdOn,
                status,
                sharingScope,
                id
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudyParticipant other = (StudyParticipant) obj;
        return Objects.equals(attributes, other.attributes) && Objects.equals(consentHistories, other.consentHistories) &&
                Objects.equals(dataGroups, other.dataGroups) && Objects.equals(email, other.email) &&
                Objects.equals(externalId, other.externalId) && Objects.equals(password, other.password) &&
                Objects.equals(firstName, other.firstName) && Objects.equals(healthCode, other.healthCode) &&
                Objects.equals(languages, other.languages) && Objects.equals(lastName, other.lastName) &&
                Objects.equals(notifyByEmail, other.notifyByEmail) && Objects.equals(roles, other.roles) &&
                Objects.equals(sharingScope, other.sharingScope) && Objects.equals(status, other.status) &&
                Objects.equals(createdOn, other.createdOn) && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "StudyParticipant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", externalId='" + externalId + '\'' +
                ", password='[REDACTED]'" +
                ", sharingScope=" + sharingScope +
                ", notifyByEmail=" + notifyByEmail +
                ", email='" + email + '\'' +
                ", dataGroups=" + dataGroups +
                ", healthCode='[REDACTED]'" +
                ", consentHistories=" + consentHistories +
                ", roles=" + roles +
                ", languages=" + languages +
                ", createdOn=" + createdOn +
                ", status=" + status +
                ", id='" + id + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String externalId;
        private String password;
        private SharingScope sharingScope;
        private Boolean notifyByEmail;
        private Set<String> dataGroups;
        private Set<Roles> roles;
        private Map<String, String> attributes;
        private LinkedHashSet<String> languages;
        private AccountStatus status;
        private String id;

        public Builder copyOf(StudyParticipant participant) {
            this.firstName = participant.firstName;
            this.lastName = participant.lastName;
            this.email = participant.email;
            this.externalId = participant.externalId;
            this.password = participant.password;
            this.sharingScope = participant.sharingScope;
            this.notifyByEmail = participant.notifyByEmail;
            this.status = participant.status;
            this.id = participant.id;
            this.dataGroups = participant.dataGroups;
            this.attributes = participant.attributes;
            this.languages = participant.languages;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        ;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        ;

        public Builder withExternalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withSharingScope(SharingScope sharingScope) {
            this.sharingScope = sharingScope;
            return this;
        }

        public Builder withNotifyByEmail(boolean notifyByEmail) {
            this.notifyByEmail = Boolean.valueOf(notifyByEmail);
            return this;
        }

        public Builder withDataGroups(Set<String> dataGroups) {
            this.dataGroups = dataGroups;
            return this;
        }

        public Builder withLanguages(LinkedHashSet<String> languages) {
            this.languages = languages;
            return this;
        }

        public Builder withAttributes(Map<String, String> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withRoles(Set<Roles> roles) {
            this.roles = roles;
            return this;
        }

        public Builder withStatus(AccountStatus status) {
            this.status = status;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public StudyParticipant build() {
            return new StudyParticipant(firstName,
                    lastName,
                    email,
                    externalId,
                    password,
                    sharingScope,
                    notifyByEmail,
                    dataGroups,
                    null,
                    attributes,
                    null,
                    roles,
                    null,
                    status,
                    languages,
                    id
            );
        }
    }
}
