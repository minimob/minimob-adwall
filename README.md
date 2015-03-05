# Android Sample for Minimob Adwall

This is a example project on how to implement minimob adwall library
 in order diplay ads on your application.

# Download and Install
Download [the latest AAR][1] and add it to your project as a module

# How to use the library:

- Extend the AddWallActivity on a new Activity class

- Implement OnCreate Method and add the code below
```groovy
        // This method is used to declare if the payout of an offer will be multiplied
        // with the last value that you provided on AddRatioRange method.
        setIsRatioMultiplier(false);

        //You can use the AddRationRange method multiple times if you need
        //to provide different credits of a payout range.

        //For example:
        //From a payout for $0.01 to $0.30 cents the credits will be 10.
        AddRatioRange(0.01,0.30,10);

        //From $0.31 to $0.80 cents payout the credits will be 20.
        AddRatioRange(0.31,0.80,20);

        //From $0.91 to $10 dollar payout the credits will be 30.
        AddRatioRange(0.81,10,30);

        //To this method you declare the credit type that user will earn on adwall.
        //For example Minutes.
        setmUnitType("Minutes");

        //To this method you must add the UserID. This is used as an identifier for user clicks.
        setmUserId("00306944508550");

        //To this method you must add your campaign ID.
        setmCampaign("default");

        //To this method you must add the Google Advertising ID.
        setmGAID("38400000-8cf0-11bd-b23e-10b96e40000d");
```


[1]:https://github.com/shermanventures/minimob_adwall/raw/master/com.minimob.adwall/com.minimob.adwall.aar
