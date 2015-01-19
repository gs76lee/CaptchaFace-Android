package com.wassa.captchaface.sample;

import android.app.Application;

import com.wassa.whatsthatface.captchaface.CaptchaUtils;

/**
 * 
 * @author Alexandre AGOSTINI - Copyright (c) 2014 Wassa. All rights reserved.
 */
public class SampleApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		/**
		 * TODO LOAD THE Captch[a]face PLUGIN
		 */
		CaptchaUtils.loadPlugin(this);
	}
}
