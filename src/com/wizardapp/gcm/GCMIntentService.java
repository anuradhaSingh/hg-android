package com.wizardapp.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.wizardapp.R;
import com.google.android.gcm.GCMBaseIntentService;
import com.wizardapp.main.LoginActivity;
import com.wizardapp.utils.Constants;


public class GCMIntentService extends GCMBaseIntentService {
 
    private static final String TAG = "GCMIntentService";
 
    public GCMIntentService() {
        super(Constants.SENDER_ID);
    }
 
    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
       Constants.displayMessage(context, "Your device registred with GCM","IHC");
      //  PushServerUtilities.register(context, "", "", registrationId);
    }
 
    /**
     * Method called on device un registred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Constants.displayMessage(context, getString(R.string.gcm_unregistered),getString(R.string.gcm_unregistered));
       // PushServerUtilities.unregister(context, registrationId);
    }
 
    /**
     * Method called on Receiving a new message
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {
        String message = intent.getExtras().getString("message");
        String title = intent.getExtras().getString("title");
         
        Constants.displayMessage(context, message,title);
        // notifies user
        generateNotification(context, message,title);
    }
 
    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        String message = getString(R.string.gcm_deleted, total);
        Constants.displayMessage(context, message,getString(R.string.app_name));
        // notifies user
        generateNotification(context, message,getString(R.string.app_name));
    }
 
    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
    	Constants.displayMessage(context, getString(R.string.gcm_error, errorId),getString(R.string.app_name));
    }
 
    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
    	Constants.displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId),getString(R.string.app_name));
        return super.onRecoverableError(context, errorId);
    }
 
    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message,String title) {
        long when = System.currentTimeMillis();
       
        int notifID =1;
         //---PendingIntent to launch activity if the user selects 
        Intent i = new Intent(context,LoginActivity.class);
        i.putExtra("NotifID", notifID);  
        PendingIntent detailsIntent =PendingIntent.getActivity(context, 0, i, 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.app_icon,message, when);
        notification.setLatestEventInfo(context, title, message, detailsIntent);
         //---100ms delay, vibrate for 250ms, pause for 100 ms and then vibrate for 500ms---
        notification.vibrate = new long[] { 100, 250, 100, 500};  
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(notifID, notification);
 
    }
 
}