package com.shermanventures.offerwalllib.ui;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aris on 15/01/2015.
 */
public class PreferencesManager {

    private static final String PREF_NAME = "PREF_NAME";
    private static final String PLAYSTORE_LINK_OPENED = "PLAYSTORE_LINK_OPENED";
    private static final String KEY_USER_COUNTRY_CODE = "KEY_USER_COUNTRY_CODE";
    private static final String KEY_USER_INTERNATIONAL_CALLING_CODE = "app.mobile.wasabee.KEY_USER_INTERNATIONAL_CALLING_CODE";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }

    public void setUserCoutryCode(String value) {
        mPref.edit()
                .putString(KEY_USER_COUNTRY_CODE, value)
                .apply();
    }

    public String getUserCountryCode() {
        return mPref.getString(KEY_USER_COUNTRY_CODE, "");
    }

    public void setUserInternationalCallingCode(String value) {
        mPref.edit()
                .putString(KEY_USER_INTERNATIONAL_CALLING_CODE, value)
                .apply();
    }

    public String getUserInternationalCallingCode() {
        return mPref.getString(KEY_USER_INTERNATIONAL_CALLING_CODE, "");
    }

    public boolean getPlayStoreLinkOpened() {
        return mPref.getBoolean(PLAYSTORE_LINK_OPENED, false);
    }

    public void setPlayStoreLinkOpened(boolean value) {
        mPref.edit()
                .putBoolean(PLAYSTORE_LINK_OPENED, value)
                .apply();
    }

}
