# SDK Android fingerPrint

## Dependency 

First add it in your root build.gradle at the end of repositories:

`
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
`

Add the dependency

`
dependencies {
	        implementation 'com.github.redeceler:fingerprint-sdk-android:Tag'
	}
`

After that, add the .aar dependency located in sdkfingerprint/libs in your lib folder.


## How to call

Is very simple to call this library: 

`
 new Fingerprint().generateFingerprint(this, false,  sessionId -> {
            Log.e("SESSION ID", sessionId);
        }     
`

