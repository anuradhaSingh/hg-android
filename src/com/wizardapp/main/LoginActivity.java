package com.wizardapp.main;

import com.example.wizardapp.R;
import com.wizardapp.fragments.LoginFragment;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
