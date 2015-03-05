package com.minimob.adwallexample;

import android.os.Bundle;
import com.minimob.adwall.activity.activity.AdWallActivity;

public class Adwall_Example extends AdWallActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Ratio Is Multiplier
        setIsRatioMultiplier(false);
        //$0.30 = 10 Minutes payout, $0.31-$0.80 = 20 Minutes payout and $0.81+ = 30 Minutes Payout
        AddRatioRange(0.01,0.30,10);
        AddRatioRange(0.31,0.80,20);
        AddRatioRange(0.81,999,30);

        setmUserId("00306944508550");
        setmCampaign("default");

        setmGAID("38400000-8cf0-11bd-b23e-10b96e40000d");
        setmUnitType("Minutes");
        setmRegistrationID("notificationID");

        super.onCreate(savedInstanceState);

    }
}
