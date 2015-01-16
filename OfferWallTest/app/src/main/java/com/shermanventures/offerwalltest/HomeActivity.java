package com.shermanventures.offerwalltest;

import android.content.Context;
import android.os.AsyncTask;
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

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.shermanventures.offerwalllib.gcm.GcmUtils;
import com.shermanventures.offerwalllib.ui.OfferWallActivity;
import com.shermanventures.offerwalllib.utils.NetworkInfo;
import com.shermanventures.offerwalllib.webview.OfferWallWebView;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;


public class HomeActivity extends OfferWallActivity implements OnGcmRegisteredListener{

    private static final String TAG = "OfferWallActivity";
    //private static String URL = "http://offerwall-appbakery.rhcloud.com/files/index.html?campaign=default&ratio=7&country=GR&gaId=00306903454321";
    private static final String SENDER_ID = "313919324471";
    private static final String CAMPAIGN_ID = "default";
    private static final String USER_ID = "6936996702";

    private Context context;
    private OfferWallRegistrar registrar;
    AtomicInteger msgId = new AtomicInteger();
    GoogleCloudMessaging gcm;
    private String mRegisrationId = "";
    private static String webViewUrl = "";

    private PlaceholderFragment mPlaceholderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = getApplicationContext();
        gcm = GoogleCloudMessaging.getInstance(this);
        // Check device for Play Services APK. If check succeeds, proceed with
        //  GCM registration. Otherwise, the library will prompt the user to install or upgrade Google Play Services.
        if (!GcmUtils.checkPlayServices(this)) {
            Log.d(TAG, "Play Services Apk is not installed");
        }
        registrar = new OfferWallRegistrar(this);
        registrar.setOnRegisteredListener(this);
        if(registrar.getRegistrationId() == null){
            registrar.registerInBackground(SENDER_ID);
        } else {
            mRegisrationId = registrar.getRegistrationId();
            webViewUrl = formatUrl(CAMPAIGN_ID,  USER_ID);
        }


        if (savedInstanceState == null) {
            mPlaceholderFragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(com.shermanventures.offerwalllib.R.id.container, mPlaceholderFragment)
                    .commit();
        }
    }

    protected void updateUI(String campaignId, String registrationId, String userId){
        if (mPlaceholderFragment != null && mPlaceholderFragment.isAdded()) {
            webViewUrl = formatUrl(campaignId, userId);
            //mPlaceholderFragment.loadWebView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GcmUtils.checkPlayServices(this);
        updateUI(CAMPAIGN_ID, mRegisrationId, USER_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    String msg = "";
                    try {
                        Bundle data = new Bundle();
                        data.putString("my_message", "Hello World");
                        data.putString("my_action", "com.google.android.gcm.demo.app.ECHO_NOW");
                        String id = Integer.toString(msgId.incrementAndGet());
                        gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
                        msg = "Sent message";
                    } catch (IOException ex) {
                        msg = "Error :" + ex.getMessage();
                    }
                    return msg;
                }

                @Override
                protected void onPostExecute(String msg) {
                    Log.d(TAG, msg);
                }
            }.execute(null, null, null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnGcmRegistered(String registrationId) {
        mRegisrationId = registrationId;
        updateUI(CAMPAIGN_ID, mRegisrationId, USER_ID);
    }

    @Override
    public void OnGcmRegistrationError(Exception exception) {

    }

    public static class PlaceholderFragment extends Fragment {

        public OfferWallWebView webView;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(com.shermanventures.offerwalllib.R.layout.fragment_offer_wall, container, false);
            NetworkInfo networkInfo = new NetworkInfo();

            webView = (OfferWallWebView) rootView.findViewById(com.shermanventures.offerwalllib.R.id.webview);
            if (!TextUtils.isEmpty(webViewUrl)) {
                if(networkInfo.hasInternetConnection(getActivity())){
                    loadWebView();
                } else {
                    webView.loadData("No Internet Connection", "text/html", "UTF-8");
                }

            }
            return rootView;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);

        }


        public void loadWebView() {
            webView.init(getActivity());
            webView.loadUrl(webViewUrl);
            Log.d(TAG, webViewUrl);
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     *//*
    public static class PlaceholderFragment extends Fragment {

        private OfferWallWebView webView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
            webView = (OfferWallWebView) rootView.findViewById(R.id.webview);
            webView.init(getActivity());
            webView.loadUrl(URL);
            return rootView;
        }
    }*/
}
