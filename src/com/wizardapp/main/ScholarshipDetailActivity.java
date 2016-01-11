package com.wizardapp.main;

import com.example.wizardapp.R;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;

public class ScholarshipDetailActivity extends MyBaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scholarship_detail);
		showCustomActionBar();
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
	}
}
