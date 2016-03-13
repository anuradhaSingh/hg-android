package com.wizardapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wizardapp.R;
import com.wizardapp.main.ScoreBoardDetailActivity;


public class ScoreBoardAdapter extends BaseAdapter{
	private Context context;
	private static LayoutInflater inflater=null;
	Holder holder;
	public ScoreBoardAdapter(Context context) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi=convertView;
		if(vi==null)
	    {
			holder = new Holder();
	    	vi = inflater.inflate(R.layout.scoreborad_list_row_view, null);
	    	holder.title=(TextView)vi.findViewById(R.id.txt_scoreboard_title);
	    	vi.setTag(holder);
	    }
	    else{
	    	 holder = (Holder) vi.getTag();
	    }
       holder.title.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(context,ScoreBoardDetailActivity.class);
				context.startActivity(intent);
			}
		});
		return vi;
	}
	@Override
	public int getCount() {
		return 6;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	static class Holder {
	    
	     TextView title;
	    
	    
	}


}
