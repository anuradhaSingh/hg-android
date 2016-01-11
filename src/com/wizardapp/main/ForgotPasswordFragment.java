package com.wizardapp.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ForgotPasswordFragment extends MyBaseFragment {
	
	private static Activity activitycontext; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    public ForgotPasswordFragment(int layout) 
   	{
   	  layout_to_inflate = layout;
   	}
   	
   	public ForgotPasswordFragment() 
   	{
   	}
   	
   	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
   	{   
   		activitycontext = getActivity();
   		final LinearLayout ll = (LinearLayout) inflater.inflate(layout_to_inflate, container, false);
   		
		return ll;
   	}

}
