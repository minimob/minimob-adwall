package com.minimob.minimobtest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.minimob.addwall.ui.AddWallActivity;
import com.minimob.addwall.utils.NetworkInfo;
import com.minimob.addwall.webview.AddWallWebView;

import java.util.concurrent.atomic.AtomicInteger;


public class TestCaseActivity extends AddWallActivity {

    private static final String SENDER_ID = "313919324471";
    private static final String CAMPAIGN_ID = "default";
    private static final String USER_ID = "6934XXXXXX";

    private Context context;
    private static String webViewUrl = "";

    private PlaceholderFragment mPlaceholderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_case);
        if (savedInstanceState == null) {
            gotoAddWall();
        }
        webViewUrl = formatUrl(CAMPAIGN_ID,  USER_ID);
    }

    protected void gotoAddWall(){
        mPlaceholderFragment = new PlaceholderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(com.minimob.addwall.R.id.container, mPlaceholderFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_case, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateUI(CAMPAIGN_ID, USER_ID);
    }

    public static class PlaceholderFragment extends Fragment {

        public AddWallWebView webView;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(com.minimob.addwall.R.layout.fragment_add_wall, container, false);
            NetworkInfo networkInfo = new NetworkInfo();

            webView = (AddWallWebView) rootView.findViewById(com.minimob.addwall.R.id.webview);
            if (!TextUtils.isEmpty(webViewUrl)) {
                if(networkInfo.hasInternetConnection(getActivity())){
                    loadWebView();
                } else {
                    webView.loadData("No Internet Connection. Please try again.", "text/html", "UTF-8");
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

        }
    }

}
