package com.wizardapp.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.wizardapp.model.UserDetail;

public class SharedPreferencesHelper {

	public static final String SHAREDPREF_NAME = "hey";
	private static SharedPreferences  mPrefs  ;
	private static Editor prefsEditor ;
	public static final String DELIVERY_DATE = "delivery_date";
	
	
	public static void setmPrefs(SharedPreferences mPrefs) {
		SharedPreferencesHelper.mPrefs = mPrefs;
		SharedPreferencesHelper.prefsEditor = mPrefs.edit();
	}

	public static void setLoggedUserInfo(UserDetail userResponse){
		Gson gson = new Gson();
        String json = gson.toJson(userResponse);
        prefsEditor.putString(Constants.SharedPref.loggedInUserData, json);
       // prefsEditor.commit();
        prefsEditor.apply();
	}
	
	public static UserDetail getLoggedInUserInfo(){
		Gson gson = new Gson();
        String json = mPrefs.getString(Constants.SharedPref.loggedInUserData, "");
        UserDetail obj = gson.fromJson(json, UserDetail.class);
        return obj ;
	}
	
}
