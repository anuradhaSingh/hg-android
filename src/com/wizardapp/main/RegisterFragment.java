package com.wizardapp.main;

import com.example.wizardapp.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;



public class RegisterFragment extends MyBaseFragment {
	private static Activity activitycontext; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    static ProgressDialog progress;
    EditText firstName,lastname,emailId,dateOfBith,mobile,address,pinCode;
    Button register;
    public RegisterFragment(int layout) 
	{
	  layout_to_inflate = layout;
	}
	
	public RegisterFragment() 
	{
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{   
		activitycontext = getActivity();
	    final LinearLayout ll = (LinearLayout) inflater.inflate(layout_to_inflate, container, false);
	    firstName=(EditText)ll.findViewById(R.id.firstname_edittext);
	    lastname=(EditText)ll.findViewById(R.id.lastname_edittext);
	    emailId=(EditText)ll.findViewById(R.id.email_edittext);
	    dateOfBith=(EditText)ll.findViewById(R.id.dateofbirth_edittext);
	    mobile=(EditText)ll.findViewById(R.id.mobile_edittext);
	    address=(EditText)ll.findViewById(R.id.address_edittext);
	    pinCode=(EditText)ll.findViewById(R.id.pincode_edittext);
	    register=(Button)ll.findViewById(R.id.register_btn);
		 return ll;
			
	}
			
			@Override
			public void onCreate(Bundle savedInstanceState) 
			{
				try 
				{
					super.onCreate(savedInstanceState);
					bundle = getArguments();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

}
