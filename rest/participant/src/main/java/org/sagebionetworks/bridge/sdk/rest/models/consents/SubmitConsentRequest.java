package org.sagebionetworks.bridge.sdk.rest.models.consents;

import java.util.Objects;

import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.sagebionetworks.bridge.sdk.rest.models.accounts.SharingScope;

/**
 * Created by liujoshua on 6/8/16.
 */
public class SubmitConsentRequest {

    /**
     * User's name
     */
    public final String name;

    /**
     * User's birthdate
     */
    public final LocalDate birthdate;

    /**
     * User's signature image data
     */
    public final String imageData;

    /**
     * User's signature image mime type
     */
    public final String imageMimeType;

    /**
     * User's sharing scope choice
     */
    public final SharingScope scope;

    /**
     * <p>
     * Simple constructor.
     * </p>
     * <p>
     * imageData and imageMimeType are optional. However, if one of them is specified, both of them must be specified.
     * If they are specified, they must be non-empty.
     * </p>
     *
     * @param name          name of the user giving consent, must be non-null and non-empty
     * @param birthdate     user's birth date, must be non-null
     * @param imageData     signature image data as a Base64 encoded string, optional
     * @param imageMimeType signature image MIME type (ex: image/png), optional
     */
    public SubmitConsentRequest(String name, LocalDate birthdate, String imageData, String imageMimeType, SharingScope scope) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(birthdate);
        Objects.requireNonNull(scope);

        this.name = name;
        this.birthdate = birthdate;
        this.imageData = imageData;
        this.imageMimeType = imageMimeType;
        this.scope = scope;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SubmitConsentRequest other = (SubmitConsentRequest) obj;
        return (Objects.equals(birthdate, other.birthdate) && Objects.equals(imageData, other.imageData) &&
                Objects.equals(imageMimeType, other.imageMimeType) && Objects.equals(name, other.name));
    }

    @Override
    public String toString() {
        return String.format(
                "ConsentSignature[name=%s, birthdate=%s, hasImageData=%s, imageMimeType=%s]",
                name,
                birthdate.toString(ISODateTimeFormat.date()),
                (imageData != null),
                imageMimeType
        );
    }
}
