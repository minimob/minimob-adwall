package com.shermanventures.offerwalltest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.shermanventures.offerwalllib.gcm.RegisterGcm;

/**
 * Created by aris on 13/12/2014.
 */
public class OfferWallRegistrar extends RegisterGcm {
    /**
     * Creates a new instance of {@link com.shermanventures.offerwalllib.gcm.RegisterGcm}.
     *
     * @param context application's context.
     */
    private static final String TAG = "RegistrationId";
    private OnGcmRegisteredListener mOnGcmRegisteredListener;

    public OfferWallRegistrar(Context context) {
        super(context);
    }

    public void setOnRegisteredListener(Activity activity) {
        try {
            mOnGcmRegisteredListener = (OnGcmRegisteredListener) activity;
        } catch (ClassCastException cce) {
            Log.d(TAG, "Must implement OnGcmRegisteredListener");
        }
    }

    @Override
    protected void onRegister(String registrationId) {
        Log.d(TAG, registrationId);
        mOnGcmRegisteredListener.OnGcmRegistered(registrationId);
    }

    @Override
    protected void onError(Exception exception) {
        Log.e(TAG, exception.toString());
        mOnGcmRegisteredListener.OnGcmRegistrationError(exception);
    }
}
