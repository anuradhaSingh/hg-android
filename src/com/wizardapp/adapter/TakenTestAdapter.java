package com.wizardapp.adapter;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wizardapp.adapter.AvailableTestAdapter.Holder;
import com.wizardapp.apis.QuestionApi;
import com.wizardapp.apis.ScholarshipApi;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.model.UserScholarshipResult;
import com.wizardapp.services.QuestionService;
import com.wizardapp.utils.SharedPreferencesHelper;

public class TakenTestAdapter extends BaseAdapter implements QuestionService{
	private Activity context;
	private static LayoutInflater inflater=null;
	Holder holder;
	Dialog dialogpopUp;
	private List<Scholarship> takenList;
	private UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
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
	    	holder.startTest.setTag(avalable);
	    }
	    else{
	    	 holder = (Holder) vi.getTag();
	    }
		holder.title.setText(avalable.getScholarshipName());
       holder.startTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Scholarship schlarship = (Scholarship) v.getTag();
				QuestionApi.getScoreResult(context, TakenTestAdapter.this, schlarship.getId(), userData.getId());
				
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
	 private void showScorePopUp(UserScholarshipResult userdetailResult) {
		  dialogpopUp = new Dialog(context);
		  context.getWindow().setBackgroundDrawable(
		    new ColorDrawable(android.graphics.Color.TRANSPARENT));
		  Window window = dialogpopUp.getWindow();
		  window.setBackgroundDrawableResource(android.R.color.transparent);
		  window.requestFeature(window.FEATURE_NO_TITLE);
		  dialogpopUp.setContentView(R.layout.score_card);
		  TextView texttotal_question=(TextView)dialogpopUp.findViewById(R.id.texttotal_question);
		  TextView textcorrect_answer=(TextView)dialogpopUp.findViewById(R.id.textcorrect_answer);
		  TextView textwrong_answer=(TextView)dialogpopUp.findViewById(R.id.textwrong_answer);
		  TextView text_rank=(TextView)dialogpopUp.findViewById(R.id.text_rank);
		  texttotal_question.setText("Total Questions : "+(userdetailResult.getCurrectQuestion()+userdetailResult.getWrongQuestion()));
		  textcorrect_answer.setText("Correct Answer : "+userdetailResult.getCurrectQuestion());
		  textwrong_answer.setText("Wrong Answer : "+userdetailResult.getWrongQuestion());
		  text_rank.setText("rank : "+userdetailResult.getRank());
		  ImageView buttnSuccess = (ImageView) dialogpopUp.findViewById(R.id.imageclose);
		  buttnSuccess.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogpopUp.dismiss();
			}
		});
		  
		  dialogpopUp.show();

		 }

	@Override
	public void getQuestionsList(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitAnswer(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getResult(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getResultForScore(String response) {
		// TODO Auto-generated method stub
		try{
			if(null != response){
				Type type = new TypeToken<UserScholarshipResult>(){}.getType();
		        UserScholarshipResult userdetail= new GsonBuilder().create().fromJson(response, type);
				showScorePopUp(userdetail);
			}else{
				Toast.makeText(context, "Network Or Api Issue", Toast.LENGTH_SHORT).show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	 
}