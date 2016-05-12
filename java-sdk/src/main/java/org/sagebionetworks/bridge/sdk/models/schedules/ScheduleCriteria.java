package org.sagebionetworks.bridge.sdk.models.schedules;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import org.sagebionetworks.bridge.sdk.models.Criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ScheduleCriteria {
    private final Schedule schedule;
    private final Criteria criteria;
    
    @JsonCreator
    public ScheduleCriteria(@JsonProperty("schedule") Schedule schedule, 
            @JsonProperty("criteria") Criteria criteria) {
        checkNotNull(schedule);
        checkNotNull(criteria);
        this.schedule = schedule;
        this.criteria = criteria;
    }
    public Schedule getSchedule() {
        return schedule;
    }
    public Criteria getCriteria() {
        return criteria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule, criteria);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ScheduleCriteria other = (ScheduleCriteria) obj;
        return Objects.equals(schedule, other.schedule) && Objects.equals(criteria, other.criteria);
    }
    @Override
    public String toString() {
        return "ScheduleCriteria [schedule=" + schedule + ", criteria=" + criteria + "]";
    }
}
