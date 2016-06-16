package org.sagebionetworks.bridge.sdk;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.LinkedHashSet;

import org.sagebionetworks.bridge.rest.BridgeParticipantMapper;
import org.sagebionetworks.bridge.sdk.exceptions.ConsentRequiredException;
import org.sagebionetworks.bridge.sdk.models.accounts.EmailCredentials;
import org.sagebionetworks.bridge.sdk.models.accounts.SignInCredentials;
import org.sagebionetworks.bridge.sdk.models.accounts.StudyParticipant;
import org.sagebionetworks.bridge.sdk.rest.AuthenticationService;
import org.sagebionetworks.bridge.sdk.rest.models.users.SignUpRequest;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUser;
import org.sagebionetworks.bridge.sdk.rest.models.users.StudyUserCredentials;
import org.sagebionetworks.bridge.sdk.utils.Utilities;

import com.fasterxml.jackson.databind.node.ObjectNode;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ClientProvider {
    
    private static final Config CONFIG = new Config();
    
    private static ClientInfo INFO = new ClientInfo.Builder().build();

    private final static AuthenticationService AUTHENTICATION_SERVICE;
    
    private static LinkedHashSet<String> LANGUAGES = new LinkedHashSet<>();

    static {
        Retrofit r = new Retrofit.Builder().baseUrl(CONFIG.getEnvironment().getUrl())
                .addConverterFactory(JacksonConverterFactory.create(new BridgeParticipantMapper().getMapper())).build();
        AUTHENTICATION_SERVICE = r.create(AuthenticationService.class);
    }
    
    /**
     * Retrieve the Config object for the system.
     *
     * @return Config
     */
    public static Config getConfig() {
        return CONFIG;
    }
    
    public static ClientInfo getClientInfo() {
        return INFO;
    }
    
    public static synchronized void setClientInfo(ClientInfo clientInfo) {
        INFO = checkNotNull(clientInfo);
    }
    
    public static void addLanguage(String language) {
        checkArgument(isNotBlank(language));
        
        // Would like to parse and verify this as a LanguageRange object,
        // which fully supports Accept-Language header syntax. It's a Java 8
        // feature and we're on Java 7. Put it on the TODO list.
        LANGUAGES.add(language);
    }
    
    public static LinkedHashSet<String> getLanguages() {
        return Utilities.newLinkedHashSet(LANGUAGES);
    }
    
    public static void clearLanguages() {
        LANGUAGES = new LinkedHashSet<>();
    }

    /**
     * Sign In to Bridge with the given credentials. Returns a session object to manipulate. Method is idempotent.
     *
     * @param signIn
     *            The credentials you wish to sign in with.
     * @return Session
     */
    public static Session signIn(SignInCredentials signIn) throws ConsentRequiredException {
        checkNotNull(signIn, "SignInCredentials required.");

        org.sagebionetworks.bridge.sdk.rest.models.users.Session session = new BaseApiCaller(null).executeCall(AUTHENTICATION_SERVICE.signIn(Utilities.getObjectAsType(signIn,
                StudyUserCredentials.class)));

        UserSession userSession = Utilities.getObjectAsType(session, UserSession.class);
        return new BridgeSession(userSession);
    }

    /**
     * Sign Up an account with Bridge using the given credentials.
     *
     * @param studyId
     *      The identifier of the study the participant is signing up with
     * @param participant
     *      The participant to create an account for
     */
    public static void signUp(String studyId, StudyParticipant participant) {
        checkArgument(isNotBlank(studyId), "Study ID required.");
        checkNotNull(participant, "StudyParticipant required.");

        ObjectNode node = Utilities.getMapper().valueToTree(participant);
        node.put("study", studyId);

        SignUpRequest signUpRequest = Utilities.getJsonAsType(node, SignUpRequest.class);;
        new BaseApiCaller(null).executeCall(AUTHENTICATION_SERVICE.signUp(signUpRequest));
    }
    
    /**
     * Resend an email verification request to the supplied email address.
     * 
     * @param email
     *      Email credentials associated with a Bridge account.
     */
    public static void resendEmailVerification(EmailCredentials email) {
        checkNotNull(email, "EmailCredentials required");

        StudyUser studyUser = Utilities.getObjectAsType(email, StudyUser.class);

        new BaseApiCaller(null).executeCall(AUTHENTICATION_SERVICE.resendEmailVerificataion(studyUser));
    }

    /**
     * Request your password be reset. A link to change the password will be sent to the provided email.
     *
     * @param email
     *            Email credentials associated with a Bridge account.
     */
    public static void requestResetPassword(EmailCredentials email) {
        checkNotNull(email, "EmailCredentials required");

        StudyUser studyUser = Utilities.getObjectAsType(email, StudyUser.class);

        new BaseApiCaller(null).executeCall(AUTHENTICATION_SERVICE.requestResetPassword(studyUser));
    }
}
