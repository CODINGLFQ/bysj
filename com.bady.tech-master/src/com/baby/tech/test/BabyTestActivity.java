package com.baby.tech.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.baby.tech.R;


public class BabyTestActivity extends Activity {
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	//private Button mBackButton;
	private TextView mQuestionTextView;
	private Button mCheatButton;
	private static final String TAG = "Quzi1Activity";
	private static final String KEY_INDEX = "index";
	private static final String KEY_ISCHEATER = "ischeater";
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
		new TrueFalse(R.string.question_ocean, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true),		
	};
	private int mCurrentIndex = 0;
	//private boolean mIsCheater;
	private void updateQuestion(){
		 int question = mQuestionBank[mCurrentIndex].getQuestion();
	     mQuestionTextView.setText(question);
	}
	private void checkAnswer(boolean userPressedTure){
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageId = 0;
		
		if(mQuestionBank[mCurrentIndex].isIscheater()){
			messageId = R.string.judgment_toast;
		}
		else{
			if(userPressedTure == answerIsTrue){
				messageId = R.string.correct_toast;
			}else{
				messageId = R.string.incorrect_toast;
			}
		}

		Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onstart(Bundle) called");
        setContentView(R.layout.activity_babytest);
        //获取地理只是问题字符串的资源Id
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        //mBackButton = (Button) findViewById(R.id.back_button);
        //定义点击字可以跳转ֿ�����ת
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();
			}
		});
       //定义true按键
        mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Quzi1Activity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
				checkAnswer(true);
			}
		});
        //定义false按键
        mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(Quzi1Activity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
				checkAnswer(false);
			}
		});
        //定义next按键
        mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				//mIsCheater = false;
				updateQuestion();
			}
		});
        //cheat按键设置
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//start CheatActivity
				Intent i = new Intent(BabyTestActivity.this, AnswerActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(AnswerActivity.EXTRA_ANSWER_IS_TURE, answerIsTrue);
				//需要从子activity获取返回信息时，用的方法
				startActivityForResult(i, 0);
			}
		});
        
//        //定义back按键
//        mBackButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
//				updateQuestion();
//			}
//		});
        if(savedInstanceState != null){
        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        	//mIsCheater = savedInstanceState.getBoolean(KEY_ISCHEATER);
        	mQuestionBank[mCurrentIndex].setIscheater(savedInstanceState.getBoolean(KEY_ISCHEATER));		
        	
        }
    }
	
	//保存以前数据的实现方法
	public void onSavedInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstancestte");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
		//savedInstanceState.putBoolean(KEY_ISCHEATER, mIsCheater);
		savedInstanceState.putBoolean(KEY_ISCHEATER,
				mQuestionBank[mCurrentIndex].isIscheater());
	}
	    @Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			Log.d(TAG,"onStart() called:");
		}
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			Log.d(TAG,"onResume() called:");
		}
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			Log.d(TAG,"onPause() called:");
		}
		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			Log.d(TAG,"onStop() called:");
		}
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			Log.d(TAG,"onDestroy() called:");
		}
		
		//处理返回结果
		@Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if(data == null){
				return;
			}
			//要从Extra中回去数据的方法
			//mIsCheater = data.getBooleanExtra(CheatActivity.EXRA_ANSWER_IS_SHOWEN, false);
			mQuestionBank[mCurrentIndex].setIscheater(data.getBooleanExtra(AnswerActivity.
					EXRA_ANSWER_IS_SHOWEN, false));
		}
		
   
    
}
