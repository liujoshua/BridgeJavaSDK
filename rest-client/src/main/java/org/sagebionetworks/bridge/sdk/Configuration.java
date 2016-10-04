package org.sagebionetworks.bridge.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.sagebionetworks.bridge.sdk.json.LowercaseEnumModule;
import org.sagebionetworks.bridge.sdk.models.holders.GuidCreatedOnVersionHolder;
import org.sagebionetworks.bridge.sdk.models.holders.GuidHolder;
import org.sagebionetworks.bridge.sdk.models.holders.GuidVersionHolder;
import org.sagebionetworks.bridge.sdk.models.holders.SimpleGuidCreatedOnVersionHolder;
import org.sagebionetworks.bridge.sdk.models.holders.SimpleGuidHolder;
import org.sagebionetworks.bridge.sdk.models.holders.SimpleGuidVersionHolder;

/**
 * Created by liujoshua on 10/4/16.
 */
public class Configuration {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule mod = new SimpleModule();
        mod.addAbstractTypeMapping(GuidHolder.class, SimpleGuidHolder.class);
        mod.addAbstractTypeMapping(GuidVersionHolder.class, SimpleGuidVersionHolder.class);
        mod.addAbstractTypeMapping(GuidCreatedOnVersionHolder.class, SimpleGuidCreatedOnVersionHolder.class);
        MAPPER.registerModule(mod);
        MAPPER.registerModule(new JodaModule());
        MAPPER.registerModule(new LowercaseEnumModule());
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
