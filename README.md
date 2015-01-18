# minimob_adwall
An Android library to display adds to your app.

#Download and installation
You have to include the maven repository into the build.gradle of your app, example:
```maven
repositories{
    maven{
        url "https://oss.sonatype.org/content/groups/public"
    }
}
```
then you can grab it via maven:
```xml
<dependency>
  <groupId>com.minimob</groupId>
  <artifactId>library</artifactId>
  <version>1.0.2-SNAPSHOT</version>
  <type>aar</type>
</dependency>
```
or Gradle:
```groovy
compile 'com.minimob:library:1.0.2-SNAPSHOT@aar'
```

# How to use the library:
- Extend the AddWallActivity and create a new instance of AddWallWebView.
- Override the formatUrl method and pass the user id and campaign id.
- finally load the url with the AddWallWebView instance

see the minimobTest sample application.
