package org.sagebionetworks.bridge.sdk.rest.models.users;

/**
 * Created by liujoshua on 6/8/16.
 */

import java.util.Objects;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.sagebionetworks.bridge.sdk.rest.models.subpopulations.SubpopulationGuid;

public class StudyUserConsentHistory {

    private final SubpopulationGuid subpopulationGuid;
    private final DateTime consentCreatedOn;
    private final String name;
    private final LocalDate birthdate;
    private final String imageData;
    private final String imageMimeType;
    private final DateTime signedOn;
    private final DateTime withdrewOn;
    private final boolean hasSignedActiveConsent;

    // Users will never create this object, so using constructor with final fields.
    StudyUserConsentHistory(SubpopulationGuid subpopGuid,
                            DateTime consentCreatedOn,
                            String name,
                            LocalDate birthdate,
                            String imageData,
                            String imageMimeType,
                            DateTime signedOn,
                            DateTime withdrewOn,
                            boolean hasSignedActiveConsent) {
        this.subpopulationGuid = subpopGuid;
        this.consentCreatedOn = consentCreatedOn;
        this.name = name;
        this.birthdate = birthdate;
        this.imageData = imageData;
        this.imageMimeType = imageMimeType;
        this.signedOn = signedOn;
        this.withdrewOn = withdrewOn;
        this.hasSignedActiveConsent = hasSignedActiveConsent;
    }

    public SubpopulationGuid getSubpopulationGuid() {
        return subpopulationGuid;
    }

    public DateTime getConsentCreatedOn() {
        return consentCreatedOn;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getImageData() {
        return imageData;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public DateTime getSignedOn() {
        return signedOn;
    }

    public DateTime getWithdrewOn() {
        return withdrewOn;
    }

    public boolean isHasSignedActiveConsent() {
        return hasSignedActiveConsent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                birthdate,
                consentCreatedOn,
                hasSignedActiveConsent,
                imageData,
                imageMimeType,
                name,
                signedOn,
                subpopulationGuid,
                withdrewOn
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StudyUserConsentHistory other = (StudyUserConsentHistory) obj;
        return Objects.equals(birthdate, other.birthdate) && Objects.equals(consentCreatedOn, other.consentCreatedOn) &&
                Objects.equals(hasSignedActiveConsent, other.hasSignedActiveConsent) && Objects.equals(imageData, other.imageData) &&
                Objects.equals(imageMimeType, other.imageMimeType) && Objects.equals(name, other.name) &&
                Objects.equals(signedOn, other.signedOn) && Objects.equals(subpopulationGuid, other.subpopulationGuid) &&
                Objects.equals(withdrewOn, other.withdrewOn);
    }

    @Override
    public String toString() {
        return "StudyUserConsentHistory{" +
                "subpopulationGuid=" + subpopulationGuid +
                ", consentCreatedOn=" + consentCreatedOn +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", imageData='" + imageData + '\'' +
                ", imageMimeType='" + imageMimeType + '\'' +
                ", signedOn=" + signedOn +
                ", withdrewOn=" + withdrewOn +
                ", hasSignedActiveConsent=" + hasSignedActiveConsent +
                '}';
    }
}
