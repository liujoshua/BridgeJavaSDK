package org.sagebionetworks.bridge.sdk.models.accounts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import org.sagebionetworks.bridge.sdk.Configuration;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class StudyUserCredentialsTest {
    
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(StudyUserCredentials.class).suppress(Warning.NONFINAL_FIELDS).allFieldsShouldBeUsed().verify();
    }
    
    @Test
    public void canSerializer() throws Exception {
        StudyUserCredentials signIn = new StudyUserCredentials("study-key", "email@email.com", "password");
        
        JsonNode node = Configuration.getMapper().valueToTree(signIn);
        assertEquals("study-key", node.get("study").asText());
        assertEquals("email@email.com", node.get("username").asText());
        assertEquals("email@email.com", node.get("email").asText());
        assertEquals("password", node.get("password").asText());
    }
    
}
