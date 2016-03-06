package com.wizardapp.adapter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.wizardapp.apis.ScholarshipApi;
import com.wizardapp.main.MyTestActivity;
import com.wizardapp.main.ScholarshipDetailActivity;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.PaymentServices;
import com.wizardapp.services.ScholarshipPrimaryServices;
import com.wizardapp.utils.SharedPreferencesHelper;

public class ScholarshipTestAdapter extends BaseAdapter implements PaymentServices,ScholarshipPrimaryServices{
	private Activity context;
	private static LayoutInflater inflater=null;
	Holder holder;
	Dialog dialogpopUp = null;
	private List<Scholarship> scholarshipList;
	private UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
	public ScholarshipTestAdapter(Activity context ,List<Scholarship> scholarList) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.scholarshipList = scholarList;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
    Scholarship schlarship=scholarshipList.get(position);
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
		holder.title.setText(""+schlarship.getScholarshipName());
		holder.total_price.setText(" Prize : Rs. "+schlarship.getPrizeMoney());
		holder.total_pay.setText(" Fee : Rs. "+schlarship.getScholarshipFees());
		holder.discount.setText(" discount : Rs. 0.0");
        holder.buy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Scholarship schlarship = (Scholarship) v.getTag();
				
				ScholarshipApi.buyScholarship(context, ScholarshipTestAdapter.this, schlarship.getId(), userData.getId());
			}
		});
		
		holder.thumb_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(context,ScholarshipDetailActivity.class);
				context.startActivity(intent);
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
	    
	    ImageView thumb_image;
	    TextView title;
	    TextView total_price;
	    TextView discount;
	    TextView total_pay;
	    Button buy;
	    
	}
	
	 private void showPaymentPopUp(final Long scholarshipBuyId) {
		  dialogpopUp = new Dialog(context);
		  context.getWindow().setBackgroundDrawable(
		    new ColorDrawable(android.graphics.Color.TRANSPARENT));
		  Window window = dialogpopUp.getWindow();
		  window.setBackgroundDrawableResource(android.R.color.transparent);
		  window.requestFeature(window.FEATURE_NO_TITLE);
		  dialogpopUp.setContentView(R.layout.payment);
		  Button buttnSuccess = (Button) dialogpopUp.findViewById(R.id.btn_success);
		  final JSONObject jObj = new JSONObject();
		  try{
			  jObj.put("userScholarshipDetailId", scholarshipBuyId);
			  jObj.put("paymentMode", "from mobile app");
			  jObj.put("paymentComment", "This is testing");
			  jObj.put("apiResponse", "nothing");
			  jObj.put("transactionId", "nothing");
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  buttnSuccess.setOnClickListener(new OnClickListener() {
		   @Override
		   public void onClick(View v) {
			   try {
				jObj.put("paymentStatus", "success");
				ScholarshipApi.payForScholarship(context, ScholarshipTestAdapter.this, jObj, scholarshipBuyId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }

		  });
		  Button btnFail = (Button) dialogpopUp.findViewById(R.id.btn_failure);
		  btnFail.setOnClickListener(new OnClickListener() {
		   @Override
		   public void onClick(View v) {
			   try {
				jObj.put("paymentStatus", "fail");
				ScholarshipApi.payForScholarship(context, ScholarshipTestAdapter.this, jObj, scholarshipBuyId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }

		  });
		  
		  dialogpopUp.show();

		 }

	@Override
	public void payForTest(String response, Long userScholarshipDetailId) {
		try{
			if(null != response){
				Intent intent =new Intent(context,MyTestActivity.class);
				context.startActivity(intent);
				context.finish();
		        dialogpopUp.dismiss();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAllByClassNumber(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDetailById(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyScholarship(String response) {
		try{
			if(null != response){
				showPaymentPopUp(Long.valueOf(response));
			}else{
				Toast.makeText(context, "Network Or Api Issue", Toast.LENGTH_SHORT).show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void tobuyTestList(String response) {
		// TODO Auto-generated method stub
		
	}

}
