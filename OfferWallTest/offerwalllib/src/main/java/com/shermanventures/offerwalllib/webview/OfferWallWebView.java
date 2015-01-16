package com.shermanventures.offerwalllib.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shermanventures.offerwalllib.ui.PreferencesManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aris on 13/12/2014.
 */
public class OfferWallWebView extends WebView {

    private static final String TAG = "OfferWallWebView";
    private int mRedirectedCount = 0;
    private List<String> urlList = new ArrayList<String>();
    private Context context;
    private OfferWallWebClient webClient;

    public OfferWallWebView(Context context) {
        super(context);
        init(context);
    }

    public OfferWallWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OfferWallWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /*Initialize and configure the webView*/
    public void init(Context context) {
        this.context = context;
        setWebViewClient(new OfferWallWebClient());
        WebSettings s = getSettings();
        s.setBuiltInZoomControls(false);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(false);
        s.setSaveFormData(false);
        s.setJavaScriptEnabled(true);

        // enable navigator.geolocation
        //s.setGeolocationEnabled(true);
        //s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");

        // enable Web Storage: localStorage, sessionStorage
        s.setDomStorageEnabled(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((OfferWallWebView.this != null) && canGoBack()){
                goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * Internal Webclient that catch @OfferWallWebView user events.
     * In our case that means, after user selects a link, catches all redirect urls until
     * playstore url {must load and view the web url and then call playstore app, because with reverse sequence
     * breaks the web history}.
     * */
    private class OfferWallWebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            super.shouldOverrideUrlLoading(view, url);
            Log.d(TAG, "LoadUrl: " + url.toString());
            mRedirectedCount++;
            if (checkPlaystore(url)) {
                if(url.startsWith("http:") || url.startsWith("https:")){
                    String[] parts = url.split("apps/");
                    String part1 = parts[0];
                    String appDetail = parts[1];
                    Log.d(TAG, appDetail);
                    view.stopLoading();
                    view.clearHistory();
                    try {
                        PreferencesManager.getInstance(context).setPlayStoreLinkOpened(true);
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://"+appDetail)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://"+appDetail)));
                    }

                } else {
                    view.stopLoading();
                    view.clearHistory();
                    try {
                        PreferencesManager.getInstance(context).setPlayStoreLinkOpened(true);
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                }
            }

            return false;
        }

        public boolean checkPlaystore(String url) {
            if (url.startsWith("https://play.google.com/store") || url.startsWith("http://play.google.com/store")
                    || url.startsWith("market://")) {
                URL urlPlay = null;
                try {
                    urlPlay = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                //Log.d(TAG, "Play Url: " + urlPlay.);
                return true;
            }
            return false;
        }
    }


}
