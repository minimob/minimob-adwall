package com.shermanventures.offerwalltest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shermanventures.offerwalllib.gcm.GcmBroadcastReceiver;
import com.shermanventures.offerwalllib.gcm.GcmIntentService;

public class OfferWallReceiver extends GcmBroadcastReceiver{

    private static final String TAG = "OFFERWALLRECEIVER";


    public OfferWallReceiver() {
        Log.d(TAG, "GCM trigger");
    }

    @Override
    protected String getGcmIntentServiceClassName(Intent intent) {
        return OfferWallIntentService.class.getName();
    }
}
