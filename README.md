# minimob_adwall
An Android library to display adds to your app.

#Download and installation
Download [the latest JAR][1] or grab via Maven:


You have to include the maven repository into the build.gradle of your app, example:
```maven
repositories{
    maven{
        mavenCentral()
    }
}
```
then you can grab it via maven:
```xml
<dependency>
  <groupId>com.minimob</groupId>
  <artifactId>library</artifactId>
  <version>1.0.5</version>
  <type>aar</type>
</dependency>
```
or Gradle:
```groovy
compile 'com.minimob:library:1.0.6'
```

# How to use the library:
- Extend the AddWallActivity and create a new instance of AddWallWebView.
- Override the formatUrl method and pass the user id, campaign id, ratio and label properties. If you need a range for the ratio, override the addRatio method.
- finally load the url with the AddWallWebView instance

see the minimobTest sample application.

[1]:https://github.com/shermanventures/minimob_adwall/blob/master/addwall.jar/raw=true
