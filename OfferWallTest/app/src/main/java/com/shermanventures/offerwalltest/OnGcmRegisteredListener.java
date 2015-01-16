package com.shermanventures.offerwalltest;

/**
 * Created by aris on 15/01/2015.
 */
public interface OnGcmRegisteredListener {
    public void OnGcmRegistered(String registrationId);
    public void OnGcmRegistrationError(Exception exception);
}
