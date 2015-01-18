# minimob_adwall

#Download and installation
You have to include the maven repository into the build.gradle of your app, example:

repositories{
    maven{
        url "https://oss.sonatype.org/content/groups/public"
    }
}

then you can grab it via maven:
```xml
<dependency>
  <groupId>com.minimob</groupId>
  <artifactId>library</artifactId>
  <version>1.0.2-SNAPSHOT</version>
  <type>aar</type>
</dependency>
```
```groovy
or Gradle:
compile 'com.minimob:library:1.0.2-SNAPSHOT@aar'
```

# How to use the library:
- Extend the AddWallActivity and create a new instance of AddWallWebView.
- Override the formatUrl method and pass the user id and campaign id.
- finally load the url with the AddWallWebView instance
