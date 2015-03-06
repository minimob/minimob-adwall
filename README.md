# Android Sample for Minimob Adwall

This is a example project on how to implement minimob adwall library
 in order diplay ads on your application.

## How it works

To use the minimob adwall library you must extend your Activity class with our AddWallActivity class.
When you start your Activity you will see the Adwall inside the activity.


## How to use the library:

- Download [the latest AAR][1] and add it to your project as a module or Download [the latest Jar][2] and add it as a library

- Create a new Activity class and change it to extend the AddWallActivity.

- Do not forget to import com.minimob.adwall.activity.activity.AdWallActivity.

- Implement OnCreate Method on your Activity class and add the code below:

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

        //To this method you declare the credit type that users will earn on adwall.
        //For example Minutes.
        setmUnitType("Minutes");

        //To this method you must add the UserID. This is used as an identifier for user clicks.
        setmUserId("00306944508550");

        //To this method you must add your campaign ID.
        setmCampaign("your_campaign_ID");

        //To this method you must add the Google Advertising ID.
        setmGAID("38400000-8cf0-11bd-b23e-10b96e40000d");

        //The super.OnCreate must be called last
        super.onCreate(savedInstanceState);
```

- From your project create an Intent to start your Activity Class and the offerwall will open.

##Methods

### setIsRatioMultiplier

This method is used to declare if the payout of an offer will be multiplied with the last value that you provided on AddRatioRange method.

```groovy
//For example:
setIsRatioMultiplier(false)
```

####AddRatioRange
On this method you define the credits that user will earn based on payout.
You can use the AddRationRange method multiple times if you need
to provide different credits of a payout range.

```groovy
//For example:
//From a payout for $0.01 to $0.30 cents the credits will be 10.
AddRatioRange(0.01,0.30,10);

//From $0.31 to $0.80 cents payout the credits will be 20.
AddRatioRange(0.31,0.80,20);

//From $0.91 to $10 dollar payout the credits will be 30.
AddRatioRange(0.81,10,30);
```

####setmUnitType
To this method you must declare the credit type that users will earn on adwall.
This is only for user information
```groovy
//For example Minutes.
setmUnitType("Minutes");
```
####setmUserId

To this method you must add the UserID. This is used as an identifier for user clicks.
```groovy
//For example the user mobile number. (MSISDN)
setmUserId("00306944508585");
```


####setmCampaign

To this method you must add your campaign ID that minimob will provide to you. 
```groovy
//For example.
setmCampaign("your_campaign_ID");
```

####setmGAID
To this method you must add the Google Advertising ID.
```groovy
//For example.
setmGAID("38400000-8cf0-11bd-b23e-10b96e40000d");
```



[1]:https://github.com/shermanventures/minimob_adwall/raw/master/com.minimob.adwall/com.minimob.adwall.aar
[2]:https://github.com/shermanventures/minimob_adwall/raw/master/com.minimob.adwall/com.minimob.adwall.jar
