package org.sagebionetworks.bridge.sdk.models.schedules;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ScheduleCriteriaTest {
    @Test
    public void equalsHashCode() {
        EqualsVerifier.forClass(ScheduleCriteria.class).allFieldsShouldBeUsed().verify();
    }
}
