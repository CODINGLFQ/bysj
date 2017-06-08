package com.baby.tech.fragment;

import net.youmi.android.diy.DiyManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baby.tech.R;
import com.baby.tech.activity.MainActivity;
import com.baby.tech.base.MyApplication;
import com.baby.tech.login.LoginActivity;
import com.baby.tech.test.AnswerActivity;
import com.baby.tech.test.BabyTestActivity;
import com.baby.tech.test.TrueFalse;

public class Fragment03 extends BaseFragment {
	
	private static final String TAG = "CollectFragment";
	private TextView mTitleTv;
	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	//private Button mBackButton;
	private TextView mQuestionTextView;
	private Button mCheatButton;
//	private static final String TAG = "Quzi1Activity";
	private static final String KEY_INDEX = "index";
	private static final String KEY_ISCHEATER = "ischeater";
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
		new TrueFalse(R.string.question_ocean, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true),
		new TrueFalse(R.string.question_yi ,false),
		new TrueFalse(R.string.question_er, true),
		new TrueFalse(R.string.question_san,false),
		new TrueFalse(R.string.question_si , true),
		new TrueFalse(R.string.question_wu, false),
	};
	private int mCurrentIndex = 0;

	
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

//		Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
		Toast.makeText(getActivity(), messageId, Toast.LENGTH_SHORT).show();
	}	
//     这行里是在Fragment里面，makeText里面的参数不能用this,要用getActivity()
//	makeText方法的第一个参数指的是上下文的对象，你这里上下文的对象是这个activity本身，所以写成activity.this代表本身	
	
//=============================================================
	TextView textView;
	private Context context;
	MyApplication myApplication;
//=============================================================
	
	public static Fragment03 newInstance() {
		Fragment03 collectFragment = new Fragment03();

		return collectFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onstart(Bundle) called");
//        setContentView(R.layout.activity_babytest);
//        //获取地理只是问题字符串的资源Id
//        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
//        updateQuestion();
//        
//        mTrueButton = (Button) findViewById(R.id.true_button);
//        mFalseButton = (Button) findViewById(R.id.false_button);
//        mNextButton = (ImageButton) findViewById(R.id.next_button);
//        //mBackButton = (Button) findViewById(R.id.back_button);
//        //定义点击字可以跳转ֿ�����ת
//        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//				updateQuestion();
//			}
//		});
//       //定义true按键
//        mTrueButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//Toast.makeText(Quzi1Activity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
//				checkAnswer(true);
//			}
//		});
//        //定义false按键
//        mFalseButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//Toast.makeText(Quzi1Activity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
//				checkAnswer(false);
//			}
//		});
//        //定义next按键
//        mNextButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//				//mIsCheater = false;
//				updateQuestion();
//			}
//		});
//        //cheat按键设置
//        mCheatButton = (Button) findViewById(R.id.cheat_button);
//        mCheatButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//start CheatActivity
//				Intent i = new Intent(BabyTestActivity.this, AnswerActivity.class);
//				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
//				i.putExtra(AnswerActivity.EXTRA_ANSWER_IS_TURE, answerIsTrue);
//				//需要从子activity获取返回信息时，用的方法
//				startActivityForResult(i, 0);
//			}
//		});
//        
////        //定义back按键
////        mBackButton.setOnClickListener(new View.OnClickListener() {
////			
////			@Override
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////				mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
////				updateQuestion();
////			}
////		});
//        if(savedInstanceState != null){
//        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
//        	//mIsCheater = savedInstanceState.getBoolean(KEY_ISCHEATER);
//        	mQuestionBank[mCurrentIndex].setIscheater(savedInstanceState.getBoolean(KEY_ISCHEATER));		
//        	
//        }
//		
//        
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_babytest, container, false);
	    //View view = inflater.inflate(R.layout.activity_babytest, container, false);
     context = ((MainActivity) getActivity()).getContext();
     
     
     
     
     //setContentView(R.layout.activity_babytest);
     //获取地理只是问题字符串的资源Id
     mQuestionTextView = (TextView) view.findViewById(R.id.question_text_view);
     updateQuestion();
     
     mTrueButton = (Button) view.findViewById(R.id.true_button);
     mFalseButton = (Button) view.findViewById(R.id.false_button);
     mNextButton = (ImageButton) view.findViewById(R.id.next_button);
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
     mCheatButton = (Button) view.findViewById(R.id.cheat_button);
     mCheatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//start CheatActivity
				Intent i = new Intent(getActivity(), AnswerActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(AnswerActivity.EXTRA_ANSWER_IS_TURE, answerIsTrue);
				//需要从子activity获取返回信息时，用的方法
				startActivityForResult(i, 0);
			}
		});
     
//     //定义back按键
//     mBackButton.setOnClickListener(new View.OnClickListener() {
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
     
     
     
     
     
//     RelativeLayout rec_btnOne = (RelativeLayout) view
//             .findViewById(R.id.rec_btnOne);
//     RelativeLayout rec_btnTwo = (RelativeLayout) view
//             .findViewById(R.id.rec_btnTwo);
//
//     rec_btnOne.setOnClickListener(new btn_listen());
//     rec_btnTwo.setOnClickListener(new btn_listen());
//     myApplication = (MyApplication) getActivity().getApplication();
     return view;
	}

 class btn_listen implements OnClickListener {

     @Override
     public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(getActivity().getApplicationContext(), BabyTestActivity.class);
			//intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
//			finish();
    	 
//         switch (arg0.getId()) {
//         case R.id.rec_btnOne:
//             DiyManager.showRecommendAppWall(context);
//             break;
//         case R.id.rec_btnTwo:
//             DiyManager.showRecommendGameWall(context);
//             break;

//         default:
//             break;
         }

     }

 //}
 
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		//initViews(view);
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initViews(View view) {
		mTitleTv = (TextView) view.findViewById(R.id.title_tv);
		mTitleTv.setText("识字测验");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		
//		super.onSaveInstanceState(savedInstanceState);
		super.onCreate(outState);
		Log.i(TAG, "onSaveInstancestte");
//		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
		outState.putInt(KEY_INDEX, mCurrentIndex);
		//savedInstanceState.putBoolean(KEY_ISCHEATER, mIsCheater);
//		savedInstanceState.putBoolean(KEY_ISCHEATER,
		outState.putBoolean(KEY_ISCHEATER,
				mQuestionBank[mCurrentIndex].isIscheater());
		
		super.onSaveInstanceState(outState);
	}
	
	//处理返回结果
	@Override
//	protected void onActivityResult(int requestCode, int resultCode,
//			Intent data) {
	public void onActivityResult(int requestCode, int resultCode,
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
	
	
	@Override
	public String getFragmentName() {
		return TAG;
	}

}
