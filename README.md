# SDK Android fingerPrint
### Getting started

1. First add it in your root build.gradle at the end of repositories:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency on the app build.gradle
```java
dependencies {
	implementation 'com.github.redeceler:fingerprint-sdk-android:1.0.2'
}
```

3. add the .aar dependency located in sdkfingerprint/libs in your lib folder.
```java
sdkfingerprint/libs/finger-print.arr
// copy this file to your libs folder
```

### Usage
Is very simple to call this library, just do it.

```java
new Fingerprint().generateFingerprint(this, false, sessionId -> {
    Log.e("SESSION ID", sessionId);
}     
```

