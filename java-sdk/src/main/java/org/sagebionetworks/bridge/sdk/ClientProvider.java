package org.sagebionetworks.bridge.sdk;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.LinkedHashSet;

import org.sagebionetworks.bridge.sdk.exceptions.ConsentRequiredException;
import org.sagebionetworks.bridge.sdk.models.accounts.EmailCredentials;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyParticipant;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyUserCredentials;
import org.sagebionetworks.bridge.sdk.rest.AuthenticationService;
import org.sagebionetworks.bridge.sdk.rest.RestClientProvider;
import org.sagebionetworks.bridge.sdk.utils.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientProvider {
    
    private static final Config config = new Config();
    
    private static ClientInfo info = new ClientInfo.Builder().build();

    private static AuthenticationService authenticationService = new RestClientProvider(config.getEnvironment().getUrl(), getClientInfo()
            .toString(), null)
            .getAuthenticationService();
    
    private static LinkedHashSet<String> languages = new LinkedHashSet<>();

    /**
     * Retrieve the Config object for the system.
     *
     * @return Config
     */
    public static Config getConfig() {
        return config;
    }
    
    public static ClientInfo getClientInfo() {
        return info;
    }
    
    public static synchronized void setClientInfo(ClientInfo clientInfo) {
        info = checkNotNull(clientInfo); 
    }
    
    public static void addLanguage(String language) {
        checkArgument(isNotBlank(language));
        
        // Would like to parse and verify this as a LanguageRange object,
        // which fully supports Accept-Language header syntax. It's a Java 8
        // feature and we're on Java 7. Put it on the TODO list.
        languages.add(language);    
    }
    
    public static LinkedHashSet<String> getLanguages() {
        return Utilities.newLinkedHashSet(languages);
    }
    
    public static void clearLanguages() {
        languages = new LinkedHashSet<>();
    }

    /**
     * Sign In to Bridge with the given credentials. Returns a session object to manipulate. Method is idempotent.
     *
     * @param signIn
     *            The credentials you wish to sign in with.
     * @return Session
     */
    public static BridgeSession signIn(StudyUserCredentials signIn) throws ConsentRequiredException {
        checkNotNull(signIn, "StudyUserCredentials required.");

        // fix
        return new BridgeSession(authenticationService.signIn(signIn).execute().body());
    }

    /**
     * Sign Up an account with Bridge using the given credentials.
     *
     * @param studyId
     *      The identifier of the study the participant is signing up with
     * @param participant
     *      Participant information for the account (email and password are the minimal information
     *      required, but any portion of the information you wish to save for a participant may 
     *      be saved at sign up).
     */
    public static void signUp(String studyId, StudyParticipant participant) {
        checkArgument(isNotBlank(studyId), "Study ID required.");
        checkNotNull(participant, "StudyParticipant required.");

        signUp(new StudyParticipant.Builder().copyOf(participant).withStudyId(studyId).build());
    }

    /**
     * Sign Up an account with Bridge using the given credentials.
     *
     * @param studyParticipant
     *      Participant information for the account (email and password are the minimal information
     *      required, but any portion of the information you wish to save for a participant may
     *      be saved at sign up).
     */
    public static void signUp(StudyParticipant studyParticipant) {
        checkNotNull(studyParticipant, "StudyParticipant required.");

        // exec
        authenticationService.signUp(studyParticipant);
    }
    
    /**
     * Resend an email verification request to the supplied email address.
     * 
     * @param email
     *      Email credentials associated with a Bridge account.
     */
    public static void resendEmailVerification(EmailCredentials email) {
        checkNotNull(email, "EmailCredentials required");

        // need to execute and handle errors
        authenticationService
                .resendEmailVerification(convert(email));
    }

    /**
     * Request your password be reset. A link to change the password will be sent to the provided email.
     *
     * @param email
     *            Email credentials associated with a Bridge account.
     */
    public static void requestResetPassword(EmailCredentials email) {
        checkNotNull(email, "EmailCredentials required");

        // need to execute and handle errors
        authenticationService.requestResetPassword(convert(email));
    }

    private static StudyUserCredentials convert(EmailCredentials email) {
        return new StudyUserCredentials(email.getStudyIdentifier(), email.getEmail(), "placeholder");
    }
}
