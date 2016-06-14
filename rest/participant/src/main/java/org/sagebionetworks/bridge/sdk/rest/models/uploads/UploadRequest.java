package org.sagebionetworks.bridge.sdk.rest.models.uploads;

import java.util.Objects;

/**
 * Created by liujoshua on 5/11/16.
 */
public final class UploadRequest {
    public final String name;
    public final long contentLength;
    public final String contentMd5;
    public final String contentType;
    
    public UploadRequest(String name, long contentLength, String contentMd5, String contentType) {
        this.name = name;
        this.contentLength = contentLength;
        this.contentMd5 = contentMd5;
        this.contentType = contentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contentLength, contentMd5, contentType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UploadRequest other = (UploadRequest) obj;
        return Objects.equals(contentLength, other.contentLength) && Objects.equals(contentMd5, other.contentMd5) &&
                Objects.equals(contentType, other.contentType) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return String.format("UploadRequest [name=%s, contentMd5=%s, contentType=%s, contentLength=%s]",
                name,
                contentMd5,
                contentType,
                contentLength
        );
    }
}
