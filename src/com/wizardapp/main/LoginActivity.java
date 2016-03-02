package com.wizardapp.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.wizardapp.R;
import com.wizardapp.fragments.LoginFragment;
import com.wizardapp.fragments.RegisterFragment;
import com.wizardapp.utils.DateUtil;
import com.wizardapp.utils.SharedPreferencesHelper;

public class LoginActivity extends MyBaseActivity{

	SharedPreferences  mPrefs  ;
	Editor prefsEditor ;
	 public static final int DATE_OF_BIRTH = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPrefs = getPreferences(MODE_PRIVATE);
	    prefsEditor = mPrefs.edit();
	    SharedPreferencesHelper.setmPrefs(mPrefs);
	    SharedPreferencesHelper.setPrefsEditor(prefsEditor);
	       LoginFragment login_fragment = new LoginFragment(R.layout.login);
		   navigateTo(login_fragment,login_fragment.getClass().getSimpleName());
		  
	}

	   @Override
			protected void onDestroy() 
		    {
				super.onDestroy();
			}
		    
		    @Override
		    protected void onResume() 
		    {
		       super.onResume();
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
							startDate = DateUtil.parse(RegisterFragment.dateOfBith.getText().toString());
							OnDateSetListener listener = new DateSetListener(RegisterFragment.dateOfBith);
							int year = DateUtil.getYear(startDate);
							int month = DateUtil.month(startDate);
							int dayOfMonth = DateUtil.dayOfMonth(startDate);
							r = new DatePickerDialog(this, listener, year, month, dayOfMonth);
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					catch (ParseException e) {
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
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					Calendar c = Calendar.getInstance();
					c.set(year, monthOfYear, dayOfMonth);
					updateButtonDisplay(button, c.getTime());
				}
			}
			private static void updateButtonDisplay(Button button, Date dateToSet) {
				button.setText(new SimpleDateFormat("dd-MM-yyyy").format(dateToSet));
			}

		    }
