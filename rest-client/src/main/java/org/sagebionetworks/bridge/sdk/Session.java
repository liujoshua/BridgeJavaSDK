package org.sagebionetworks.bridge.sdk;

import java.util.Map;

import org.sagebionetworks.bridge.sdk.models.accounts.StudyParticipant;
import org.sagebionetworks.bridge.sdk.models.subpopulations.ConsentStatus;
import org.sagebionetworks.bridge.sdk.models.subpopulations.SubpopulationGuid;

public interface Session {

    Map<SubpopulationGuid,ConsentStatus> getConsentStatuses();

    void checkSignedIn();

    String getSessionToken();
    
    StudyParticipant getStudyParticipant();

    boolean isConsented();
    
    boolean isSignedIn();
    
    void signOut();

}
