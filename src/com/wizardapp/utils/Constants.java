package com.wizardapp.utils;

import java.io.InputStream;
import java.io.OutputStream;

import com.google.android.gcm.GCMRegistrar;
import com.wizardapp.main.LoginActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class Constants {
	public static int isNextQuestion=0;
	public static final String httpGet = "GET";
	public static final String httpPost = "POST";
	public static final String httpPut = "PUT";
	public static final String httpDelete = "DELETE";
	public static final String loginFailed = "Invalid Creadentials";
	public static final String connection_error = "Something went wrong for the Connection ";
	public static final String tokenFailed = "Failed To recieve token"; 
	public static final String passwordMisMatch = "Password Mismatch";
	public static final String mobileNumUpdated = "Mobile number successfully updated";
	public static final String SENDER_ID="heyapp-1265";
	public static final boolean isTestMode = true;
	
	 public static void CopyStream(InputStream is, OutputStream os)
	    {
	        final int buffer_size=1024;
	        try
	        {
	            byte[] bytes=new byte[buffer_size];
	            for(;;)
	            {
	              int count=is.read(bytes, 0, buffer_size);
	              if(count==-1)
	                  break;
	              os.write(bytes, 0, count);
	            }
	        }
	        catch(Exception ex){}
	    }
	 interface SharedPref{
			String loggedInUserData = "LoggedInUserData";
		}
		 public final static boolean isValidEmail(CharSequence target) {
			  	  return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
			  
			}
		  /**
		     * Tag used on log messages.
		     */
		    static final String TAG = "AndroidHive GCM";
		 
		    public static final String DISPLAY_MESSAGE_ACTION =
		            "com.indiahomes.pushnotifications.DISPLAY_MESSAGE";
		    public static final String EXTRA_MESSAGE = "message";
		 
		    /**
		     * Notifies UI to display a message.
		     * <p>
		     * This method is defined in the common helper because it's used both by
		     * the UI and the background service.
		     *
		     * @param context application's context.
		     * @param message message to be displayed.
		     */
		    public static void displayMessage(Context context, String message,String title) {
		        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
		        intent.putExtra(EXTRA_MESSAGE, message);
		        intent.putExtra(EXTRA_MESSAGE, title);
		        context.sendBroadcast(intent);
		    }
		    public static  String getDeviceID(Activity activity)
		     {
		    	TelephonyManager phonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
		    	 String id = phonyManager.getDeviceId();
		    	 if (id == null)
		    	 {
		    	  id = "not available";
		    	 }
		    	 int phoneType = phonyManager.getPhoneType();
		    	 switch(phoneType)
		    	 {
		    	   case TelephonyManager.PHONE_TYPE_NONE:
		    	   return "NONE: " + id;
		    	   case TelephonyManager.PHONE_TYPE_GSM:
		    	   return "GSM: IMEI=" + id;
		    	   case TelephonyManager.PHONE_TYPE_CDMA:
		    	   return "CDMA: MEID/ESN=" + id;
		          default:
		    	   return "UNKNOWN: ID=" + id;
		    	 }
		      }
		   
}
