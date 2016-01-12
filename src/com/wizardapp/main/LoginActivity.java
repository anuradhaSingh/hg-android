package com.wizardapp.main;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import com.example.wizardapp.R;
import com.wizardapp.fragments.LoginFragment;
import com.wizardapp.utils.SharedPreferencesHelper;

public class LoginActivity extends MyBaseActivity{

	SharedPreferences  mPrefs  ;
	Editor prefsEditor ;
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
		    
		    
		    }
