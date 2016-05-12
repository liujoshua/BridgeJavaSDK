package org.sagebionetworks.bridge.sdk.models.users;

import java.util.Objects;

/**
 * Created by liujoshua on 5/11/16.
 */
public class EmailBody
{

    /**
     * The identifier for the study under which the user is signing in
     */
    public final String study;

    /**
     * The email address of the user's account
     */
    public final String email;

    public EmailBody(String study, String email)
    {
        this.study = Objects.requireNonNull(study);
        this.email = Objects.requireNonNull(email);
    }
}