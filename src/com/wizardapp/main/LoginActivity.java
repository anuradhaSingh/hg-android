package com.wizardapp.main;

import android.os.Bundle;

import com.example.wizardapp.R;
import com.wizardapp.fragments.LoginFragment;

public class LoginActivity extends MyBaseActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
