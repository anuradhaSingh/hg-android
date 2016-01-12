package com.wizardapp.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.ScholarshipTestAdapter;

public class ScholarshipActivity extends MyBaseActivity {
	ListView listview;
	 SimpleSideDrawer slide_me;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scholarship_list);
		slide_me = new SimpleSideDrawer(this);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		showCustomActionBar();
		
		listview=(ListView)findViewById(R.id.scholarship_listview);
		listview.setAdapter(new ScholarshipTestAdapter(ScholarshipActivity.this));
				
		
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
		RelativeLayout action_drawer=(RelativeLayout)findViewById(R.id.action_drawer);
		action_drawer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.toggleRightDrawer();
			}
		});
	}
}
