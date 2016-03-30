package com.wizardapp.gcm;

import android.content.Context;
import android.os.PowerManager;

public class WakeLocker {

	 static final String SENDER_ID = "903913289319"; 
	 
	 private static PowerManager.WakeLock wakeLock;
	 
	    public static void acquire(Context context) {
	        if (wakeLock != null) wakeLock.release();
	 
	        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
	                PowerManager.ACQUIRE_CAUSES_WAKEUP |
	                PowerManager.ON_AFTER_RELEASE, "WakeLock");
	        wakeLock.acquire();
	    }
	 
	    public static void release() {
	        if (wakeLock != null) wakeLock.release(); wakeLock = null;
	    }
}
