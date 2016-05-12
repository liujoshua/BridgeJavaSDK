package org.sagebionetworks.bridge.sdk.models.studies;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class EmailTemplate {
    
    public enum MimeType {
        TEXT("text/plain"),
        HTML("text/html");
        
        private MimeType(String type){
            this.type = type;
        }
        private final String type;
        @Override public String toString() {
            return type;
        }
    }

    private final String subject;
    private final MimeType mimeType;
    private final String body;
    
    @JsonCreator
    public EmailTemplate(@JsonProperty("subject") String subject, @JsonProperty("body") String body,
                    @JsonProperty("mimeType") MimeType mimeType) {
        checkArgument(isNotBlank(subject));
        checkArgument(isNotBlank(body));
        checkNotNull(mimeType);
        this.subject = subject;
        this.mimeType = mimeType;
        this.body = body;
    }
    
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    
    public MimeType getMimeType() {
        return mimeType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(body);
        result = prime * result + Objects.hashCode(subject);
        result = prime * result + Objects.hashCode(mimeType);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        EmailTemplate other = (EmailTemplate) obj;
        return (Objects.equals(body, other.body) && Objects.equals(subject, other.subject) && 
                Objects.equals(mimeType, other.mimeType));
    }

    @Override
    public String toString() {
        return String.format("EmailTemplate [subject=%s, body=%s, mimeType=%s]", subject, body, mimeType);
    }

}
