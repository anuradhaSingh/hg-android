package com.wizardapp.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.google.android.gcm.GCMRegistrar;
import com.wizardapp.fragments.LoginFragment;
import com.wizardapp.fragments.MobileVerificationFragment;
import com.wizardapp.fragments.RegisterFragment;
import com.wizardapp.gcm.PushServerUtilities;
import com.wizardapp.gcm.WakeLocker;
import com.wizardapp.model.UserDetail;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.DateUtil;
import com.wizardapp.utils.SharedPreferencesHelper;

public class LoginActivity extends MyBaseActivity {
	 String regId;
	SharedPreferences mPrefs;
	public static final int DATE_OF_BIRTH = 0;
	// Asyntask
    AsyncTask<Void, Void, Void> mRegisterTask;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mPrefs = getPreferences(MODE_PRIVATE);
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferencesHelper.setmPrefs(mPrefs);
		// Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
 
        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);
       // registerReceiver(mHandleMessageReceiver, new IntentFilter(Constants.DISPLAY_MESSAGE_ACTION));
        
        // Get GCM registration id
         regId =GCMRegistrar.getRegistrationId(LoginActivity.this);
       System.out.println("registration id is "+regId);
        // Check if regid already presents
        if (regId.equals("")) {
            // Registration is not present, register now with GCM           
            GCMRegistrar.register(this, Constants.SENDER_ID);
        } else {
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.              
            //  Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
            } else {

                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
                final Context context = this;
                
              
                    	 TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    	
                      
                    }
 
                   
        }
	}

	

	@Override
	protected void onResume() {
		super.onResume();
		UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
		if(null == userData){
			LoginFragment login_fragment = new LoginFragment(R.layout.login,regId);
			navigateTo(login_fragment, login_fragment.getClass().getSimpleName());
		}else{
			if(userData.isUserVerified()){
				Intent intent=new Intent(this,MyTestActivity.class);
				startActivity(intent);
				this.finish();
			}else{
				MobileVerificationFragment mobile_frag = new MobileVerificationFragment(R.layout.mobile_verification,true);
				navigateTo(mobile_frag, mobile_frag.getClass().getSimpleName());
			}
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog r = null;
		switch (id) {
		case LoginActivity.DATE_OF_BIRTH:
			try {
				Date startDate;
				try {
					startDate = DateUtil.parse(RegisterFragment.dateOfBith
							.getText().toString());
					OnDateSetListener listener = new DateSetListener(
							RegisterFragment.dateOfBith);
					int year = DateUtil.getYear(startDate);
					int month = DateUtil.month(startDate);
					int dayOfMonth = DateUtil.dayOfMonth(startDate);
					r = new DatePickerDialog(this, listener, year, month,
							dayOfMonth);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		}
		return r;
	}

	public static class DateSetListener implements OnDateSetListener {
		private Button button;

		public DateSetListener(Button button) {
			super();
			this.button = button;
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			Calendar c = Calendar.getInstance();
			c.set(year, monthOfYear, dayOfMonth);
			updateButtonDisplay(button, c.getTime());
		}
	}

	private static void updateButtonDisplay(Button button, Date dateToSet) {
		button.setText(new SimpleDateFormat("dd-MM-yyyy").format(dateToSet));
	}


    

/**
* Receiving push messages
* */
private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
@Override
public void onReceive(Context context, Intent intent) {
    String newMessage = intent.getExtras().getString(Constants.EXTRA_MESSAGE);
    // Waking up mobile if it is sleeping
    WakeLocker.acquire(getApplicationContext());
     
    /**
     * Take appropriate action on this message
     * depending upon your app requirement
     * For now i am just displaying it on the screen
     * */
     
    // Showing received message
    Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
     
    // Releasing wake lock
    WakeLocker.release();
}
};

@Override
protected void onDestroy() {
if (mRegisterTask != null) {
    mRegisterTask.cancel(true);
}
try {
    unregisterReceiver(mHandleMessageReceiver);
    GCMRegistrar.onDestroy(this);
} catch (Exception e) {
    Log.e("UnRegister Receiver Error", "> " + e.getMessage());
}
super.onDestroy();
}

}