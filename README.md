# Captchaface

CaptchaFace is a tool that allows you to know if a mobile user is really human by identification of several visual steps through the camera.

## Installation

1. Download the [latest bundle](https://github.com/wassafr/CaptchaFace-Android/archive/master.zip).
2. Drag and drop the **armeabi-v7a** directory from the archive in your project navigator under **libs**.
3. Drag and drop the **wassa** directory from the archive in your project navigator under **assets**.
4. Drag and drop the **wassa-captchaface.jar** from the archive in your project navigator under **libs** and include it in built-path.

## Usage

To run the example project, clone the repo.

Make sure you also see [CaptchaFace documentation](http://wassafr.github.io/CaptchaFace-Android/wassa-captchaface-1.0.0-jdoc/index.html).

###Basics
1. Add the following code to your **AndroidManifest.xml** 

	```xml
	
		<uses-permission
	        android:name="android.permission.CAMERA"
	        android:required="true" />
	    <uses-permission
	        android:name="android.permission.READ_EXTERNAL_STORAGE"
	        android:required="false" />
	    <uses-permission
	        android:name="android.permission.INTERNET"
	        android:required="false" />
	    <uses-permission
	        android:name="android.permission.ACCESS_NETWORK_STATE"
	        android:required="false" />
	
	    <uses-feature
	        android:name="android.hardware.camera"
	        android:required="true" />
	    <uses-feature
	        android:name="android.hardware.camera.autofocus"
	        android:required="false" />
	    <uses-feature
	        android:name="android.hardware.camera.front"
	        android:required="false" />
	    <uses-feature
	        android:name="android.hardware.camera.front.autofocus"
	        android:required="false" />
	        
    ```


2. Plugin and files loading - Add the following line to your **Application.java** , onCreate()

    ```java
    
        CaptchaUtils.loadPlugin(this);
        
    ```

3. Render - Add the following line to your **layout_activity.xml**

    ```xml
    
        <com.wassa.noyau.capture.input.KFrameRender
            android:id="@+id/render"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
            
    ```
    
4. Allow "Keep Screen ON" - Add the following line to your onCreate.

    ```java
    
		// this.setContentView(your_layout_activity.xml);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
    ```

5. Setup your first CaptchaFace service and set your own **LICENCE_KEY** - Add the following code to your onResume()

    ```java
    
    	// Define and setup captchaface service
        if (!CaptchaUtils.isInit()) 
        {
            CaptchaUtils
            	.createInstance(this, LICENCE_KEY)    
            	.load(KInputCamId.CAMERA_ID_FRONT); // Default camera (id) to select
        }
        
    ```
    ```java
    	
    	// Retrieve captchaface service anywhere
        final CaptchaServiceStandalone service = CaptchaUtils.getInstance();
        
    ```
    ```java
    
    	// Attach onFinish listener
        service.setOnFinished(new CaptchaOnFinished() {
            @Override
            public void onFinished(long _faceId, long _total, long _success) {
                // YOUR CODE
            }
        });
        
    ```
    ```java
    	
    	// Attach onFailed listener
        service.setOnFailed(new CaptchaOnFailed() {
            @Override
            public void onFailed(int _codeError) {
                // YOUR CODE
                // _codeError = 1 -> Network is required to lauch captchaface.
                // _codeError = 2 -> Unable to contact captchaface server.
                // _codeError = 3 -> The supplied licence key is invalid.
            }
        });
        
    ```

6. Be smart and allow to release the service during background - Add the following line to your onPause() (You may have to use the main thread with **Handler**)

   ```java
   
	   	// Destroy current instance
        CaptchaUtils.destroyInstance();  
  
   ```

7. Submit a default or random scenario

    ```java
    
    	// A default scenario
        service.submitScenario();
        
    ```

    ```java
    
    	// Random scenario
    	// min_random : Minimum action to do
    	// min_random : Maximum action to do
        service.submitScenario(min_random, max_random, max_time_to_process, time_to_wait_before_next_event);
        
    ```
    
8. (bis) Submit your own scenario
    
    ```java
    
    	// Custom scenario
        service.submitScenario(array_of_motion_event, max_time_to_process, time_to_wait_before_next_event);
        
    ```
    
 
9. Start the record and the preview

    ```java
    
    	// Start camera record
        service.record(MyActivity.this, (KFrameRender) findViewById(R.id.render), true, true);
        
    ```

10. Start the scenario !

    ```java
    
        service.startScenario();

    ```
    

## Requirements

* Eclipse 4.3+ , http://eclipse.org
* Android SDK 14+ , http://developer.android.com/sdk/installing/index.html
* Android NDK (Optional) , https://developer.android.com/tools/sdk/ndk/index.html
* Arch : armeabi-v7a

## Author

Wassa, contact@wassa.fr
