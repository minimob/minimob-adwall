package com.shermanventures.offerwalllib.ui;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.shermanventures.offerwalllib.R;
import com.shermanventures.offerwalllib.utils.NetworkInfo;
import com.shermanventures.offerwalllib.webview.OfferWallWebView;

import java.util.Locale;
import java.util.Map;

public class OfferWallActivity extends ActionBarActivity {

    private static final String TAG = "OfferWallActivity";
    private static String ENDPOINT = "http://offerwall-appbakery.rhcloud.com/files/index.html?";
    private static String CAMPAIGN_ID = "campaign=";
    private static String RATIO = "&ratio=7";
    private static String URL_COUNTRY_CODE_PARAM = "&country=";
    private static String USER_ID = "&gaId=";
    private static String URL_REGISTRATION_ID_PARAM = "&registrationId=";
    private static String webViewUrl = "";
    private String mRegisrationId = "";
    private String userId;
    private Context context;

    private Map<String, String> mUserNetworkInfo;
    private String mUserSimCountryCode;
    private String mUserSelectedCountryCode;
    private String mUserCountryCallingCode;

    //private PlaceholderFragment mPlaceholderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_wall);
        initUserInfo();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //updateUI(mRegisrationId);
        if(PreferencesManager.getInstance(OfferWallActivity.this).getPlayStoreLinkOpened()){
            PreferencesManager.getInstance(context).setPlayStoreLinkOpened(false);
            //showOfferWallCreditsDialog();
        }
    }



    /*private void showOfferWallCreditsDialog(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(OfferWallCreditDialogFragment.DIALOG_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        *//*DialogFragment newFragment = new OfferWallCreditDialogFragment();
        newFragment.show(ft, OfferWallCreditDialogFragment.DIALOG_TAG);*//*

    }*/

    /*protected void updateUI(String campaignId, String registrationId, String userId){
        if (mPlaceholderFragment != null && mPlaceholderFragment.isAdded()) {
            webViewUrl = formatUrl(campaignId, registrationId, userId);
            //mPlaceholderFragment.loadWebView();
        }
    }*/

    protected String formatUrl(String campaignId,  String userId) {
        StringBuilder sb = new StringBuilder(ENDPOINT);
        sb
                .append(CAMPAIGN_ID)
                .append(campaignId)
                .append(RATIO)
                .append(URL_COUNTRY_CODE_PARAM)
                .append(mUserSimCountryCode)
                //.append(PreferencesManager.getInstance(OfferWallActivity.this).getUserCountryCode())
                .append(USER_ID)
                .append(userId);
                /*.append(URL_REGISTRATION_ID_PARAM)
                .append(registrationId);*/

        Log.d(TAG, sb.toString());
        return sb.toString();
    }

    private void initUserInfo() {
        mUserNetworkInfo = NetworkInfo.getUserNetworkInfo(OfferWallActivity.this);
        mUserSimCountryCode = mUserNetworkInfo.get(NetworkInfo.USER_NETWORK_INFO_COUNTRY_CODE);
        mUserSelectedCountryCode = mUserSimCountryCode;
        mUserCountryCallingCode = NetworkInfo.getCountryCallingCode(OfferWallActivity.this, mUserSimCountryCode);
        Log.d(TAG, "SimCountrycode = " + mUserSimCountryCode + " mUserCountryCallingCode = " + mUserCountryCallingCode);
    }

    private void saveUserCountryCode(String countryCode) {
        PreferencesManager.getInstance(OfferWallActivity.this).setUserCoutryCode(countryCode);
    }



}
