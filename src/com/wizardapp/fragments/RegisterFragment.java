package com.wizardapp.fragments;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wizardapp.apis.UserApi;
import com.wizardapp.main.LoginActivity;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.DateUtil;
import com.wizardapp.utils.SharedPreferencesHelper;



public class RegisterFragment extends MyBaseFragment implements UserServices{
	private static Activity activitycontext; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    static ProgressDialog progress;
    public static   EditText firstName,lastname,emailId,mobile,address,pinCode,city,password,confirm_password,classNumber;
    Button register;
    public static Button dateOfBith;
    Spinner spinState;
    CheckBox termsCondition;
    String [] states;
    LinearLayout backlayout;
    ToggleButton gender;
    private String genderText="M";
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
	    LinearLayout headerback=(LinearLayout)ll.findViewById(R.id.header_layout);
	    headerback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createNewFragment(new LoginFragment(R.layout.login));
			}
		});
	    gender=(ToggleButton)ll.findViewById(R.id.radiobtn);
	    gender.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(gender.isChecked()){
					genderText = "M";
				}else{
					genderText = "F";
				}
			}
		});
	    firstName=(EditText)ll.findViewById(R.id.firstname_edittext);
	    termsCondition=(CheckBox)ll.findViewById(R.id.checkbox);
	    city=(EditText)ll.findViewById(R.id.city_edittext);
	    states=getResources().getStringArray(R.array.state_Array);
	    lastname=(EditText)ll.findViewById(R.id.lastname_edittext);
	    emailId=(EditText)ll.findViewById(R.id.email_edittext);
	    dateOfBith=(Button)ll.findViewById(R.id.dateofbirth_edittext);
	    mobile=(EditText)ll.findViewById(R.id.mobile_edittext);
	    address=(EditText)ll.findViewById(R.id.address_edittext);
	    pinCode=(EditText)ll.findViewById(R.id.pincode_edittext);
	    spinState=(Spinner)ll.findViewById(R.id.spinStates);
	    password=(EditText)ll.findViewById(R.id.password_edittext);
	    confirm_password=(EditText)ll.findViewById(R.id.confirm_password_edittext);
	    backlayout=(LinearLayout)ll.findViewById(R.id.header_layout);
	    classNumber=(EditText)ll.findViewById(R.id.classname_edittext);
	    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activitycontext,
				android.R.layout.simple_spinner_item, states);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinState.setAdapter(dataAdapter);
		spinState.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		backlayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activitycontext.finish();
			}
		});
		updateButtonDisplay(dateOfBith, DateUtil.addDays(new Date(), -0));
		dateOfBith.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			activitycontext.showDialog(LoginActivity.DATE_OF_BIRTH);
			}
		});
	    register=(Button)ll.findViewById(R.id.register_btn);
	    register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count =0;
				String addressString = address.getText().toString();
				String pinCodeString = pinCode.getText().toString();
				String dobString = dateOfBith.getText().toString();
				String emailString = emailId.getText().toString();
				String firstString = firstName.getText().toString();
				String lastString = lastname.getText().toString();
				String phoneString = mobile.getText().toString();
				String cityString = city.getText().toString();
				String pass = password.getText().toString();
				String retypepass = confirm_password.getText().toString();
				String classname = classNumber.getText().toString();
				if(!Constants.isValidEmail(emailString)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your email id", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				
				else if(TextUtils.isEmpty(firstString)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your first name", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				
				else if(TextUtils.isEmpty(lastString)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your last name", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				
				else if(TextUtils.isEmpty(phoneString) || phoneString.length() != 10){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your phone number", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}else if(TextUtils.isEmpty(dobString)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your date of birth", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				else if(TextUtils.isEmpty(pinCodeString)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your pincode", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				else if(TextUtils.isEmpty(addressString)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need address", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}else if(termsCondition.isChecked()==false){
					Toast toast=Toast.makeText(activitycontext, "Please accept terms and conditions!", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				else if(TextUtils.isEmpty(classname)){
					Toast toast=Toast.makeText(activitycontext, "Uh ho! We will need your class", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}else if(pass.equals(retypepass)){
					if(count == 0)
						try {
							String addressValue = address.getText().toString();
							String pinCodeValue = pinCode.getText().toString();
							String dobValue = dateOfBith.getText().toString();
							String emailValue = emailId.getText().toString();
							String firstValue = firstName.getText().toString();
							String lastValue = lastname.getText().toString();
							String phoneValue = mobile.getText().toString();
							String passvalue = password.getText().toString();
							String classvalue = classNumber.getText().toString();
								JSONObject requestObj = new JSONObject();
								requestObj.put("email", emailValue);
								requestObj.put("firstName", firstValue);
								requestObj.put("lastName", lastValue);
								requestObj.put("mobile",phoneValue );
								requestObj.put("phone",phoneValue );
								requestObj.put("streetAddress", addressValue);
								requestObj.put("gender", genderText);
								requestObj.put("zipCode", pinCodeValue);
								requestObj.put("city", cityString);
								requestObj.put("classType", classvalue);
								requestObj.put("password", passvalue);
								requestObj.put("dateOfBirth", dobValue);
								requestObj.put("state", spinState.getSelectedItem().toString());
								
								UserApi.registerUser(activitycontext,RegisterFragment.this, requestObj);
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
						else
							
							Toast.makeText(activitycontext, "Uh ho! We will need your password", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(activitycontext, Constants.passwordMisMatch, Toast.LENGTH_SHORT).show();
					}
				
			}
		});
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

			@Override
			public void userLoggingIn(String userResponse) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void registerUser(String afterRegisteration) {
				try{
					if(null != afterRegisteration){
						Type type = new TypeToken<UserDetail>(){}.getType();
				        UserDetail userdetail= new GsonBuilder().create().fromJson(afterRegisteration, type);
				        SharedPreferencesHelper.setLoggedUserInfo(userdetail);
				        createNewFragment(new MobileVerificationFragment(R.layout.mobile_verification,false));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
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

			@Override
			public void verifyOTP(String response) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void isUserExist(String response) {
				// TODO Auto-generated method stub
				
			}
			
}
