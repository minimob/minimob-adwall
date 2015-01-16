package com.shermanventures.offerwalllib.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.shermanventures.offerwalllib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by aris on 15/01/2015.
 */
public class NetworkInfo {

    public static final String USER_NETWORK_INFO_OPERATOR_NAME = "operatorName";
    public static final String USER_NETWORK_INFO_OPERATOR = "operator";
    public static final String USER_NETWORK_INFO_COUNTRY_CODE = "countryCode";
    public static final String USER_NETWORK_INFO_COUNTRY_MCC = "MCC";
    public static final String USER_NETWORK_INFO_COUNTRY_MNC = "MNC";

    public static final String COUNTRY_CODE_DISPLAY_NAME = "countryCodeDisplayName";
    public static final String COUNTRY_CODE = "countryCodeDisplayName";


    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager check = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        android.net.NetworkInfo info = check.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isMobileAvailable(Context context) {
        TelephonyManager tel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((tel.getNetworkOperator() != null && tel.getNetworkOperator().equals("")) ? false : true);
    }

    public static boolean isSimReady(Context context) {
        TelephonyManager tel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        return tel.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public static ArrayList<String> getCountryNames() {
        Locale.setDefault(Locale.ENGLISH);
        final String[] countryCodes = Locale.getISOCountries();
        ArrayList<String> countries = new ArrayList<String>();

        for (String countryCode : countryCodes) {
            Locale l = new Locale("English", countryCode);
            countries.add(l.getDisplayCountry());
        }

        return countries;
    }

    public static String getCountryCallingCodeFromDisplayName(Context context, String countryDisplayName) {
        Locale.setDefault(Locale.ENGLISH);
        final String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale l = new Locale("English", countryCode);
            if (l.getDisplayCountry().equalsIgnoreCase(countryDisplayName)) {
                return getCountryCallingCode(context, countryCode);
            }
        }
        return "";
    }

    public static String getCountryCodeFromDisplayName(String countryDisplayName) {
        Locale.setDefault(Locale.ENGLISH);
        final String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale l = new Locale("English", countryCode);
            if (l.getDisplayCountry().equalsIgnoreCase(countryDisplayName)) {
                return countryCode;
            }
        }
        return "";
    }

    public static String getCountryCallingCode(Context context, String countryId) {
        String CountryZipCode = "";
        String[] countryCodes = context.getResources().getStringArray(R.array.CountryCodes);

        for (int i = 0; i < countryCodes.length; i++) {
            String[] g = countryCodes[i].split(",");
            if (g[1].trim().equals(countryId.trim())) {
                CountryZipCode = g[0];
                break;
            }
        }
        return CountryZipCode;
    }

    public static Map<String, String> getUserNetworkInfo(Context context) {

        TelephonyManager telephonyManager = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE));

        Map<String, String> userNetWorkInfo = new HashMap<String, String>();

//        operator name
        userNetWorkInfo.put(USER_NETWORK_INFO_OPERATOR_NAME, telephonyManager.getNetworkOperatorName());
        userNetWorkInfo.put(USER_NETWORK_INFO_COUNTRY_CODE, telephonyManager.getSimCountryIso().toUpperCase());

        String networkOperator = telephonyManager.getNetworkOperator();
        userNetWorkInfo.put(USER_NETWORK_INFO_OPERATOR, networkOperator);

        if (networkOperator != null) {
            if (networkOperator.length() > 3) {
                userNetWorkInfo.put(USER_NETWORK_INFO_COUNTRY_MCC, String.valueOf(Integer.parseInt(networkOperator.substring(0, 3))));
                userNetWorkInfo.put(USER_NETWORK_INFO_COUNTRY_MNC, String.valueOf(Integer.parseInt(networkOperator.substring(3))));
            }
        } else {
            Toast.makeText(
                    context,
                    "There was an error in retrieving your network information.Please make sure a Sim card is inserted",
                    Toast.LENGTH_LONG).show();
        }

        return userNetWorkInfo;
    }

}
