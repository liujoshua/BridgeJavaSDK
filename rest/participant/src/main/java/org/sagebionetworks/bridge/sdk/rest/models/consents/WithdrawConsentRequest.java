package org.sagebionetworks.bridge.sdk.rest.models.consents;

/**
 * Created by liujoshua on 6/8/16.
 */
public class WithdrawConsentRequest {
    public final String reason;

    public WithdrawConsentRequest(String reason) {
        this.reason = reason;
    }
}
