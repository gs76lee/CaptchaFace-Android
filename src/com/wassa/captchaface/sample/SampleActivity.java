package com.wassa.captchaface.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.wassa.noyau.capture.input.KFrameRender;
import com.wassa.noyau.capture.input.KInputCamId;
import com.wassa.whatsthatface.captchaface.CaptchaOnFailed;
import com.wassa.whatsthatface.captchaface.CaptchaOnFinished;
import com.wassa.whatsthatface.captchaface.CaptchaServiceStandalone;
import com.wassa.whatsthatface.captchaface.CaptchaUtils;

public class SampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_sample);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (!CaptchaUtils.isInit()) 
	    {
	        CaptchaServiceStandalone newService = CaptchaUtils.createInstance(this, "<LICENCE_KEY>");
	        newService.setRenderToMat(true);
	        newService.load(KInputCamId.CAMERA_ID_FRONT);
	    }
		
		final CaptchaServiceStandalone service = CaptchaUtils.getInstance();
		service.setOnFinished(new CaptchaOnFinished() {
	        @Override
	        public void onFinished(long _faceId, long _total, long _success) {
	            // YOUR CODE
	        }
	    });
		
		service.setOnFailed(new CaptchaOnFailed() {
	        @Override
	        public void onFailed(int _codeError) {
	            // YOUR CODE
	            // _codeError = 1 -> Network is required to lauch captchaface.
	            // _codeError = 2 -> Unable to contact captchaface server.
	            // _codeError = 3 -> The supplied licence key is invalid.
	        }
	    });
		
		service.submitScenario(3, 5, 10000, 1500);
		service.record(SampleActivity.this, (KFrameRender) findViewById(R.id.render), true, true);
		service.startScenario();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		CaptchaUtils.destroyInstance();  
	}
	
}

