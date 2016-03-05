package com.wizardapp.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.wizardapp.R;
import com.wizardapp.adapter.AvailableTestAdapter.Holder;
import com.wizardapp.model.Scholarship;

public class TakenTestAdapter extends BaseAdapter{
	private Activity context;
	private static LayoutInflater inflater=null;
	Holder holder;
	private List<Scholarship> takenList;
	public TakenTestAdapter(Activity context,List<Scholarship> takenlist) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.takenList = takenlist;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi=convertView;
		Scholarship avalable=takenList.get(position);
		if(vi==null)
	    {
			holder = new Holder();
	    	vi = inflater.inflate(R.layout.taken_list_row, null);
	    	holder.title=(TextView)vi.findViewById(R.id.taken_row_text);
	    	holder.startTest=(Button)vi.findViewById(R.id.btn_scorecard);
	    	vi.setTag(holder);
	    }
	    else{
	    	 holder = (Holder) vi.getTag();
	    }
		holder.title.setText(avalable.getScholarshipName());
       holder.startTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		
		return vi;
	}
	@Override
	public int getCount() {
		return takenList.size();
	}

	@Override
	public Object getItem(int position) {
		return takenList.get(position);
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