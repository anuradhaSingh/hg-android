package com.wizardapp.main;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.ScholarshipTestAdapter;
import com.wizardapp.apis.ScholarshipApi;
import com.wizardapp.apis.UserApi;
import com.wizardapp.fragments.RegisterFragment;
import com.wizardapp.main.LoginActivity.DateSetListener;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.DateUtil;
import com.wizardapp.utils.SharedPreferencesHelper;

public class ProfileActivity extends MyBaseActivity implements UserServices{
	SimpleSideDrawer slide_me;
	UserDetail userdata=SharedPreferencesHelper.getLoggedInUserInfo();
	TextView email_id,gender;
	EditText firstName,lastName,pincode,state,city,mobile,address,country;
	 LinearLayout  linear; ToggleButton genderB ;
	 public static final int DATE_OF_BIRTH = 0;
	 boolean state_of_drawer;
	 Spinner classNumber;
	 String [] claases;
	 public static Button dateOfBith,btn_update;
	 String genderText;
	 Dialog dialogpopUp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		TextView txt=(TextView)findViewById(R.id.change_password);
		txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopUp(userdata.getEmail());
			}
		});
		slide_me = new SimpleSideDrawer(this);
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.profile_main);
		ll.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(state_of_drawer){
					linear.setVisibility(View.INVISIBLE);
					state_of_drawer = false;
				}
				return false;
			}
		});
		
		showCustomActionBar();
		RelativeLayout back_button=(RelativeLayout)findViewById(R.id.back_button);
		back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ProfileActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		claases=getResources().getStringArray(R.array.class_Array);
		 classNumber=(Spinner)ll.findViewById(R.id.class_number);
		firstName=(EditText)findViewById(R.id.firstname_edittext);
		lastName=(EditText)findViewById(R.id.lastname_edittext);
		dateOfBith=(Button)ll.findViewById(R.id.dateofbirth);
		email_id=(TextView)findViewById(R.id.txt_email);
		mobile=(EditText)findViewById(R.id.mobile_edittext);
		state=(EditText)findViewById(R.id.txt_state);
		city=(EditText)findViewById(R.id.txt_city);
		address=(EditText)findViewById(R.id.txt_address);
		pincode=(EditText)findViewById(R.id.txt_pincode);
		gender=(TextView)findViewById(R.id.txtview_gender);
		genderB = (ToggleButton) findViewById(R.id.radiobtn);
		btn_update=(Button)findViewById(R.id.update_btn);
		ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(ProfileActivity.this,
				android.R.layout.simple_spinner_item, claases);
	 classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		classNumber.setAdapter(classAdapter);
		classNumber.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		classNumber.setSelection(classAdapter.getPosition(userdata.getClassType()));
		dateOfBith.setText(userdata.getDateOfBirth());
		//updateButtonDisplay(dateOfBith, DateUtil.addDays(new Date(), -0));
		
		dateOfBith.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		       ProfileActivity.this.showDialog(ProfileActivity.DATE_OF_BIRTH);
			}
		});
		genderText=userdata.getGender();
		firstName.setText(userdata.getFirstName());
		lastName.setText(userdata.getLastName());
		email_id.setText(userdata.getEmail());
		mobile.setText(userdata.getMobile());
		state.setText(userdata.getState());
		city.setText(userdata.getCity());
		address.setText(userdata.getStreetAddress());
		pincode.setText(userdata.getZipCode());
		gender.setText(userdata.getGender());
		if("Male".equalsIgnoreCase(userdata.getGender())){
			genderB.setChecked(true);
		}
		genderB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(genderB.isChecked()){
					genderText = "Male";
					gender.setText("Male");
				}else{
					genderText = "Female";
					gender.setText("Female");
				}
			}
		});
		btn_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count =0;
				String addressString = address.getText().toString();
				String pinCodeString = pincode.getText().toString();
				String dobString = dateOfBith.getText().toString();
				String firstString = firstName.getText().toString();
				String lastString = lastName.getText().toString();
				String phoneString = mobile.getText().toString();
				String cityString = city.getText().toString();
				
				 if(TextUtils.isEmpty(firstString)){
					Toast toast=Toast.makeText(ProfileActivity.this, "Uh ho! We will need your first name", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				
				else if(TextUtils.isEmpty(lastString)){
					Toast toast=Toast.makeText(ProfileActivity.this, "Uh ho! We will need your last name", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				
				else if(TextUtils.isEmpty(phoneString) || phoneString.length() != 10){
					Toast toast=Toast.makeText(ProfileActivity.this, "Uh ho! Invalid Phone Number", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}else if(TextUtils.isEmpty(dobString)){
					Toast toast=Toast.makeText(ProfileActivity.this, "Uh ho! We will need your date of birth", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				else if(TextUtils.isEmpty(pinCodeString)){
					Toast toast=Toast.makeText(ProfileActivity.this, "Uh ho! We will need your pincode", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
				else if(TextUtils.isEmpty(addressString)){
					Toast toast=Toast.makeText(ProfileActivity.this, "Uh ho! We will need address", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					count =1;
				}
					else {
					if(count == 0)
						try {
							String addressValue = address.getText().toString();
							String pinCodeValue = pincode.getText().toString();
							String dobValue = dateOfBith.getText().toString();
							String emailValue = userdata.getEmail();
							String firstValue = firstName.getText().toString();
							String lastValue = lastName.getText().toString();
							String phoneValue = mobile.getText().toString();
							String classname = classNumber.getSelectedItem().toString();
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
								requestObj.put("dateOfBirth", dobValue);
								requestObj.put("state", state.getText().toString());
								requestObj.put("classType", classname);
								UserApi.updateUser(ProfileActivity.this,null, requestObj);
							
						} catch (JSONException e) {
							e.printStackTrace();
						}else{
							Toast.makeText(ProfileActivity.this, "User Already Exists. Please Login", Toast.LENGTH_SHORT).show();}
						}
						
				
			}
			
		});
	}
	private void showCustomActionBar() {
		// TODO Auto-generated method stub
		final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater()
				.inflate(R.layout.custom_actionbar, null);

		// Set up your ActionBar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(actionBarLayout);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		linear=(LinearLayout)findViewById(R.id.right_menu_header);
		
		
		RelativeLayout home=(RelativeLayout)findViewById(R.id.home_button);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent=new Intent(ProfileActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		RelativeLayout action_drawer=(RelativeLayout)findViewById(R.id.action_drawer);
		action_drawer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				linear.setVisibility(View.VISIBLE);
				slide_me.toggleRightDrawer();
				state_of_drawer = true;
			}
		});
		LinearLayout profile_view=(LinearLayout)findViewById(R.id.profile_view);
		profile_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
			}
		});
		LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
		refer_friend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,RefferFriendActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout contact_us=(LinearLayout)findViewById(R.id.contact_us_view);
		contact_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,ContactUsActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
		myTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
		scoreboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,ScoreBoardActivity.class);
				startActivity(intent);
				finish();
			}
			
		});
		LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,StartTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout log_out=(LinearLayout)findViewById(R.id.log_out_view);
		log_out.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				SharedPreferencesHelper.setLoggedUserInfo(null);
				Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
		buy_test_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
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
	public void userLoggingIn(String userResponse) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registerUser(String afterRegisteration) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUser(String updateUser) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void verifyOTP(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void isUserExist(String response) {
		// TODO Auto-generated method stub
		
	}
	private void showPopUp(final String emailId) {
		  dialogpopUp = new Dialog(ProfileActivity.this);
		  getWindow().setBackgroundDrawable(
		    new ColorDrawable(android.graphics.Color.TRANSPARENT));
		  Window window = dialogpopUp.getWindow();
		  window.setBackgroundDrawableResource(android.R.color.transparent);
		  window.requestFeature(window.FEATURE_NO_TITLE);
		  dialogpopUp.setContentView(R.layout.change_password);
		  TextView emailID=(TextView)dialogpopUp.findViewById(R.id.txtEmail_id);
		  emailID.setText(""+emailId);
		  final EditText password=(EditText)dialogpopUp.findViewById(R.id.passwordtext);
		  final EditText confirmpassword=(EditText)dialogpopUp.findViewById(R.id.confirmtext);
		  Button buttnSuccess = (Button) dialogpopUp.findViewById(R.id.confirm_btn);
		 buttnSuccess.setOnClickListener(new OnClickListener() {
		   @Override
		   public void onClick(View v) {
			   
			   String pass = password.getText().toString();
				String retypepass = confirmpassword.getText().toString();
				if(pass.equals("") && retypepass.equals("")){
					Toast.makeText(ProfileActivity.this, "Uh ho! We will need your password and confirm password", Toast.LENGTH_SHORT).show();
				}else{
				if(pass.equals(retypepass)){
			   	 UserApi.changePassword(ProfileActivity.this, null, emailId, password.getText().toString());
				}else{
					Toast.makeText(ProfileActivity.this, Constants.passwordMisMatch, Toast.LENGTH_SHORT).show();
				}
				}
				 
		   }

		  });
		  dialogpopUp.show();
	 }
	@Override
	public void changePassword(String result) {
		// TODO Auto-generated method stub
		dialogpopUp.dismiss();
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog r = null;
		switch (id) {
		case ProfileActivity.DATE_OF_BIRTH:
			try {
				Date startDate;
				try {
					startDate = DateUtil.parse(userdata.getDateOfBirth());
					OnDateSetListener listener = new DateSetListener(ProfileActivity.dateOfBith);
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

	
}
