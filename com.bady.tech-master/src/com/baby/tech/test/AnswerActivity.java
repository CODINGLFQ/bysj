package com.baby.tech.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.baby.tech.R;


public class AnswerActivity extends Activity {
	public static final String EXTRA_ANSWER_IS_TURE = 
			"com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXRA_ANSWER_IS_SHOWEN = 
			"com.bignerdranch.android.geoquiz.answer_shown";
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private TextView mApiTextView;
	private static final String KEY_IS_CFEAT = "istrue";
	private boolean mIscheat;
	
	private void setAnswerShownResult(boolean isAnswerShown){
		Intent data = new Intent();
		//将Extra数据添加给intent
		data.putExtra(EXRA_ANSWER_IS_SHOWEN, isAnswerShown);
		setResult(RESULT_OK,data);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//传入布局文件的资源ID
		setContentView(R.layout.activity_answer);
		//用户按返回键的时候显示
		setAnswerShownResult(false);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TURE, false);
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		
		mApiTextView = (TextView) findViewById(R.id.APITextView);
		
		mShowAnswer = (Button) findViewById(R.id.showAnswerButtom);
		
		mApiTextView.setText("API level" + Build.VERSION.SDK_INT);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				}else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
				mIscheat = true;
			}
		});
		//当有保留的值和 is cheat是true的时候
		if(savedInstanceState != null
				&& savedInstanceState.getBoolean(KEY_IS_CFEAT)){
			if(mAnswerIsTrue){
				mAnswerTextView.setText(R.string.true_button);
			}else {
				mAnswerTextView.setText(R.string.false_button);
			}
			setAnswerShownResult(true);
			mIscheat = true;
		}
		
	}
//保留ischeat没有旋转之前的值ֵ
	public void onSavedInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		//Log.i(TAG, "onSaveInstancestte");
		savedInstanceState.putBoolean(KEY_IS_CFEAT, mIscheat);
	}
	

}
