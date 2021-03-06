package com.wizardapp.gcm;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.wizardapp.R;
import com.google.android.gcm.GCMRegistrar;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.HeyURLs;


public final class PushServerUtilities {
	 private static final int MAX_ATTEMPTS = 5;
	    private static final int BACKOFF_MILLI_SECONDS = 2000;
	    private static final Random random = new Random();
	 
	    /**
	     * Register this account/device pair within the server.
	     *
	     */
	    public static void register(final Activity context, String name, String email, final String regId) {
	    	//add url here
	       String serverUrl = "http://"+ HeyURLs.domain + "";
	       //TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("deviceId", Constants.getDeviceID(context));
	        params.put("registrationId",regId);
	        params.put("device", "Android");
	        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
	        // Once GCM returns a registration id, we need to register on our server
	        // As the server might be down, we will retry it a couple
	        // times.
	        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
	            try {
	            	Constants.displayMessage(context, context.getString( R.string.server_registering, i, MAX_ATTEMPTS),"IHC");
	                post(serverUrl, params);
	                GCMRegistrar.setRegisteredOnServer(context, true);
	                String message = context.getString(R.string.server_registered);
	                Constants.displayMessage(context, message,"IHC");
	                return;
	            } catch (IOException e) {
	                // Here we are simplifying and retrying on any error; in a real
	                // application, it should retry only on unrecoverable errors
	                // (like HTTP error code 503).
	                if (i == MAX_ATTEMPTS) {
	                    break;
	                }
	                try {
	                   Thread.sleep(backoff);
	                } catch (InterruptedException e1) {
	                    // Activity finished before we complete - exit.
	                    Thread.currentThread().interrupt();
	                    return;
	                }
	                // increase backoff exponentially
	                backoff *= 2;
	            }
	        }
	        String message = context.getString(R.string.server_register_error,
	                MAX_ATTEMPTS);
	        Constants.displayMessage(context, message,"IHC");
	    }
	 
	    /**
	     * Unregister this account/device pair within the server.
	     */
	    public static void unregister(final Context context, final String regId) {
	        String serverUrl = HeyURLs.domain + "/unregister";
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("registrationID", regId);
	        try {
	            post(serverUrl, params);
	            GCMRegistrar.setRegisteredOnServer(context, false);
	            String message = context.getString(R.string.server_unregistered);
	            Constants.displayMessage(context, message,"IHC");
	        } catch (IOException e) {
	            // At this point the device is unregistered from GCM, but still
	            // registered in the server.
	            // We could try to unregister again, but it is not necessary:
	            // if the server tries to send a message to the device, it will get
	            // a "NotRegistered" error message and should unregister the device.
	            String message = context.getString(R.string.server_unregister_error,
	                    e.getMessage());
	            Constants.displayMessage(context, message,"IHC");
	        }
	    }
	 
	    /**
	     * Issue a POST request to the server.
	     *
	     * @param endpoint POST address.
	     * @param params request parameters.
	     *
	     * @throws IOException propagated from POST.
	     */
	    private static void post(String endpoint, Map<String, String> params)
	            throws IOException {    
	         
	        URL url;
	        try {
	            url = new URL(endpoint);
	        } catch (MalformedURLException e) {
	            throw new IllegalArgumentException("invalid url: " + endpoint);
	        }
	        StringBuilder bodyBuilder = new StringBuilder();
	        Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
	        // constructs the POST body using the parameters
	        while (iterator.hasNext()) {
	            Entry<String, String> param = iterator.next();
	            bodyBuilder.append(param.getKey()).append('=')
	                    .append(param.getValue());
	            if (iterator.hasNext()) {
	                bodyBuilder.append('&');
	            }
	        }
	        String body = bodyBuilder.toString();
	        byte[] bytes = body.getBytes();
	        HttpURLConnection conn = null;
	        try {
	            Log.e("URL", "> " + url);
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setDoOutput(true);
	            conn.setUseCaches(false);
	            conn.setFixedLengthStreamingMode(bytes.length);
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type",
	                    "application/x-www-form-urlencoded;charset=UTF-8");
	            // post the request
	            OutputStream out = conn.getOutputStream();
	            out.write(bytes);
	            out.close();
	            // handle the response
	            int status = conn.getResponseCode();
	            if (status != 200) {
	              throw new IOException("Post failed with error code " + status);
	            }
	        } finally {
	            if (conn != null) {
	                conn.disconnect();
	            }
	        }
	      }
	}
