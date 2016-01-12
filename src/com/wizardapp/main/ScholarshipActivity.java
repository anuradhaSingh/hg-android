package com.wizardapp.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.ScholarshipTestAdapter;
import com.wizardapp.model.Scholarship;
import com.wizardapp.services.ScholarshipPrimaryServices;

public class ScholarshipActivity extends MyBaseActivity implements ScholarshipPrimaryServices{
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
		LinearLayout profile_view=(LinearLayout)findViewById(R.id.profile_view);
		profile_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScholarshipActivity.this,ProfileActivity.class);
				startActivity(intent);
			}
		});
	}
	@Override
	public void getAllByClassNumber(String response) {
		try{
	        Type listType = new TypeToken<ArrayList<Scholarship>>(){}.getType();
            List<Scholarship> list = new GsonBuilder()
            .create().fromJson(response, listType);
            System.out.println(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getDetailById(String response) {
		// TODO Auto-generated method stub
		
	}
}
