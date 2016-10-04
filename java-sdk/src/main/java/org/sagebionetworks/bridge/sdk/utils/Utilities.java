package org.sagebionetworks.bridge.sdk.utils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.sagebionetworks.bridge.sdk.Configuration;
import org.sagebionetworks.bridge.sdk.exceptions.BridgeSDKException;

public final class Utilities {

    private static final String[] schemes = { "http", "https" };
    private static final UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS + UrlValidator.ALLOW_LOCAL_URLS);
    private static final EmailValidator emailValidator = EmailValidator.getInstance();
    public static final TypeReference<Map<String, Object>> TYPE_REF_RAW_MAP =
            new TypeReference<Map<String, Object>>(){};

    /**
     * This mimics the style of the toString() methods we've been writing in a more manual way.
     */
    @SuppressWarnings("serial")
    private static final class Style extends ToStringStyle {
        public Style() {
            super();
            setFieldSeparator(", ");
            setUseShortClassName(true);
            setUseIdentityHashCode(false);
        }
    }
    
    public static final ToStringStyle TO_STRING_STYLE = new Style();

    public static ObjectMapper getMapper() {
        return Configuration.getMapper();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }
        return emailValidator.isValid(email);
    }

    public static boolean isValidUrl(String url) {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null.");
        }
        return urlValidator.isValid(url);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> LinkedHashSet<T> newLinkedHashSet(LinkedHashSet<T> set) {
        LinkedHashSet<T> copy = new LinkedHashSet<T>();
        copy.addAll(set);
        return copy;
    }

    public static String getObjectAsJson(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            String message = String.format("Could not process %s: %s into JSON", object.getClass().getSimpleName(), object.toString());
            throw new BridgeSDKException(message, e);
        }
    }

    public static <T> T getJsonAsType(String json, Class<T> c) {
        try {
            return getMapper().readValue(json, c);
        } catch (IOException e) {
            throw new BridgeSDKException("Error message: " + e.getMessage()
                    + "\nSomething went wrong while converting JSON into " + c.getSimpleName()
                    + ": json=" + json, e);
        }
    }

    public static <T> T getJsonAsType(String json, TypeReference<T> type) {
        try {
            return getMapper().readValue(json, type);
        } catch (IOException e) {
            throw new BridgeSDKException("Error message: " + e.getMessage()
                    + "\nSomething went wrong while converting JSON into " + type.getType().getClass().getSimpleName()
                    + ": json=" + json, e);
        }
    }
}
