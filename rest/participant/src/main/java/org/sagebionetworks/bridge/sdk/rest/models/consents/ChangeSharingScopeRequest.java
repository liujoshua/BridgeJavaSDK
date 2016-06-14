package org.sagebionetworks.bridge.sdk.rest.models.consents;

import org.sagebionetworks.bridge.sdk.rest.models.accounts.SharingScope;

/**
 * Created by liujoshua on 6/8/16.
 */
public class ChangeSharingScopeRequest {
    public final SharingScope scope;

    public ChangeSharingScopeRequest(SharingScope scope) {
        this.scope = scope;
    }
}
