package com.wizardapp.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.paytm.pgsdk.PaytmMerchant;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
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
	int transactionCounter = 0;
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
		holder.buy.setTag(schlarship);
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
	
	/*private void showPaymentPopUp(final Long scholarshipBuyId) {
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
	 */
	 
		public void onStartTransaction(final Long scholarshipBuyId) {
			PaytmPGService Service = PaytmPGService.getStagingService();
			Map<String, String> paramMap = new HashMap<String, String>();

			// these are mandatory parameters
			
			paramMap.put("ORDER_ID", initOrderId());
			paramMap.put("MID", "WorldP64425807474247");
			paramMap.put("CUST_ID", userData.getId()+"");
			paramMap.put("CHANNEL_ID", "WAP");
			paramMap.put("INDUSTRY_TYPE_ID", "Retail");
			paramMap.put("WEBSITE", "worldpressplg");
			paramMap.put("TXN_AMOUNT", "1");
			paramMap.put("THEME", "merchant");
			paramMap.put("EMAIL", userData.getEmail());
			paramMap.put("MOBILE_NO", userData.getMobile());
			PaytmOrder Order = new PaytmOrder(paramMap);

			PaytmMerchant Merchant = new PaytmMerchant(
					"https://pguat.paytm.com/paytmchecksum/paytmCheckSumGenerator.jsp",
					"https://pguat.paytm.com/paytmchecksum/paytmCheckSumVerify.jsp");

			Service.initialize(Order, Merchant, null);

			Service.startPaymentTransaction(context, true, true,
					new PaytmPaymentTransactionCallback() {
						@Override
						public void someUIErrorOccurred(String inErrorMessage) {
							// Some UI Error Occurred in Payment Gateway Activity.
							// // This may be due to initialization of views in
							// Payment Gateway Activity or may be due to //
							// initialization of webview. // Error Message details
							// the error occurred.
						}

						@Override
						public void onTransactionSuccess(Bundle inResponse) {
							final JSONObject jObj = new JSONObject();
							transactionCounter =0;
							  try{
								  jObj.put("userScholarshipDetailId", scholarshipBuyId);
								  jObj.put("paymentMode", "from mobile app");
								  jObj.put("paymentComment", "This is testing");
								  jObj.put("apiResponse", "nothing");
								  jObj.put("transactionId", "nothing");
								  jObj.put("paymentStatus", "success");
								  ScholarshipApi.payForScholarship(context, ScholarshipTestAdapter.this, jObj, scholarshipBuyId);
							  } catch (JSONException e) {
								  e.printStackTrace();
							  }
							 
							Log.d("LOG", "Payment Transaction is successful " + inResponse);
							Toast.makeText(context, "Payment Transaction is successful ", Toast.LENGTH_LONG).show();
						}

						@Override
						public void onTransactionFailure(String inErrorMessage,
								Bundle inResponse) {
							final JSONObject jObj = new JSONObject();
							transactionCounter++;
							  try{
								  jObj.put("userScholarshipDetailId", scholarshipBuyId);
								  jObj.put("paymentMode", "from mobile app");
								  jObj.put("paymentComment", "This is testing");
								  jObj.put("apiResponse", "nothing");
								  jObj.put("transactionId", "nothing");
								  jObj.put("paymentStatus", "failure");
								  ScholarshipApi.payForScholarship(context, ScholarshipTestAdapter.this, jObj, scholarshipBuyId);
							  } catch (JSONException e) {
								  e.printStackTrace();
							  }
							Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
							Toast.makeText(context, "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
						}

						@Override 
						public void networkNotAvailable() { // If network is not
															// available, then this
															// method gets called.
						}

						@Override
						public void clientAuthenticationFailed(String inErrorMessage) {
							// This method gets called if client authentication
							// failed. // Failure may be due to following reasons //
							// 1. Server error or downtime. // 2. Server unable to
							// generate checksum or checksum response is not in
							// proper format. // 3. Server failed to authenticate
							// that client. That is value of payt_STATUS is 2. //
							// Error Message describes the reason for failure.
						}

						@Override
						public void onErrorLoadingWebPage(int iniErrorCode,
								String inErrorMessage, String inFailingUrl) {

						}

						// had to be added: NOTE
						@Override
						public void onBackPressedCancelTransaction() {
							// TODO Auto-generated method stub
						}

					});
		}

	@Override
	public void payForTest(String response, final Long userScholarshipDetailId) {
		try{
			if(null != response){
				if (transactionCounter > 0 && transactionCounter < 6) {
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
					alertDialogBuilder.setTitle("Transaction Failure! Try Again");
					alertDialogBuilder
							.setMessage("Click No to exit!")
							.setCancelable(false)
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											onStartTransaction(userScholarshipDetailId);
										}
									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
											goToHome();
										}
									});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}else{
					goToHome();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void goToHome(){
		Intent intent =new Intent(context,MyTestActivity.class);
		context.startActivity(intent);
		context.finish();
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
				//showPaymentPopUp(Long.valueOf(response));
				onStartTransaction(Long.valueOf(response));
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
	private String initOrderId() {
		Random r = new Random(System.currentTimeMillis());
		String orderId = "ORDER" + (1 + r.nextInt(2)) * 10000
				+ r.nextInt(10000);
		
		return orderId;
	}

}
