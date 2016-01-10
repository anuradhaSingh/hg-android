package com.wizardapp.adapter;

import java.util.List;

import com.example.wizardapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScholarshipTestAdapter extends BaseAdapter{
	private Context context;
	private static LayoutInflater inflater=null;
	Holder holder;
	public ScholarshipTestAdapter(Context context) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View vi=convertView;
		if(vi==null)
	    {
			holder = new Holder();
	    	vi = inflater.inflate(R.layout.scholarship_list_row_view, null);
	    	holder.thumb_image=(ImageView)vi.findViewById(R.id.scholarship_thumb_image);
	    	holder.title=(TextView)vi.findViewById(R.id.txt_scholarshiptest_title);
	    	holder.total_price=(TextView)vi.findViewById(R.id.txt_total_pay);
	    	holder.discount=(TextView)vi.findViewById(R.id.txt_discount);
	    	holder.total_pay=(TextView)vi.findViewById(R.id.txt_pay_amount);
	    	holder.buy=(Button)vi.findViewById(R.id.buy_now);
	    	 vi.setTag(holder);
	    }
	    else{
	    	 holder = (Holder) vi.getTag();
	    }
		return vi;
	}
	@Override
	public int getCount() {
		return 10;
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
	    
	    ImageView thumb_image;
	    TextView title;
	    TextView total_price;
	    TextView discount;
	    TextView total_pay;
	    Button buy;
	    
	}

}
