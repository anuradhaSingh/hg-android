package com.wizardapp.gcm;

import android.content.Context;

/**
 * @author Anuradha
 *GCM BroadcastReceiver for push notification 
 */
public class GCMBroadcastReceiver extends com.google.android.gcm.GCMBroadcastReceiver {

@Override
protected String getGCMIntentServiceClassName(Context context) {

return "com.wizardapp.gcm.GCMIntentService";
}
}