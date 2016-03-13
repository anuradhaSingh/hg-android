package com.wizardapp.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.wizardapp.R;
import com.wizardapp.main.TermsConditionActivity;
import com.wizardapp.model.Scholarship;

public class AvailableTestAdapter extends BaseAdapter{
	private Activity context;
	private static LayoutInflater inflater=null;
	Holder holder;
	private List<Scholarship> scholarshipList;
	public AvailableTestAdapter(Activity context,List<Scholarship> availableList) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.scholarshipList = availableList;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi=convertView;
		Scholarship avalable=scholarshipList.get(position);
		if(vi==null)
	    {
			holder = new Holder();
	    	vi = inflater.inflate(R.layout.available_list_row, null);
	    	holder.title=(TextView)vi.findViewById(R.id.available_row_text);
	    	holder.startTest=(Button)vi.findViewById(R.id.btn_starttest);
	    	holder.startTest.setTag(avalable);
	    	vi.setTag(holder);
	    }
	    else{
	    	 holder = (Holder) vi.getTag();
	    }
		holder.title.setText(avalable.getScholarshipName());
       holder.startTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Scholarship avai=(Scholarship) v.getTag();
				Intent intent =new Intent(context,TermsConditionActivity.class);
				intent.putExtra("object_test",avai);
				context.startActivity(intent);
				context.finish();
			}
		});
		
		
		return vi;
	}
	@Override
	public int getCount() {
		return scholarshipList.size();
	}

	@Override
	public Object getItem(int position) {
		return scholarshipList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	static class Holder {
	    
	    TextView title;
	    Button startTest;
	    
	}
	
	 
}
