package com.baby.tech.fragment;

import java.util.ArrayList;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
//import net.youmi.android.banner.AdView;
//import net.youmi.android.banner.AdViewListener;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.spot.SpotDialogListener;
//import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baby.tech.DialogTool;
import com.baby.tech.R;
import com.baby.tech.activity.MainActivity;
import com.baby.tech.base.MyApplication;
import com.baby.tech.db.DBManager;
import com.baby.tech.db.TechdbInfo;

public class Fragment01 extends BaseFragment {

	private static final String TAG = "Fragment01";

	//==========================================================
 // =======================================================================
 private TextView mTextShowone = null;
 private TextView mTextWord = null;
 private TextView mTextSentence = null;

 private MyApplication mCommonApplication;

 private String mstrAry[] = { "云", "日", "月", "山", "水", "田", "虫", "牛", "羊",
         "马", "鸭" };
 private String mstrAry2[] = { "白云", "落日", "月亮", "高山", "喝水", "农田", "虫子",
         "奶牛", "山羊", "战马", "鸭子" };
 private String mstrAry3[] = { "天上的白云飘呀飘", "天上有一轮落日", "弯弯的月亮像小船",
         "高山上有许多植物", "小朋友每天都要喝水", "农民伯伯在农田里撒种子", "小虫子爬呀爬", "会产奶的牛就是奶牛",
         "山羊会爬山", "你想骑马吗？", "小鸭子乖乖" };

 private int mDataNum = 0;
 private int mIndex = 0;
 private int mReadTotal = 0;
 private static int MAX_NUM = 20;

 private LinearLayout mDictSelectLayout = null;
 private TextView mDictSelectBtn = null;

 private TextView mDictSelectBtn13 = null;
 private TextView mDictSelectBtn35 = null;

 private Button mBtnLeft = null;
 private Button mBtnRight = null;

// private AdView adView = null;

 private DBManager mDBManager = null;
 private ArrayList<TechdbInfo> mTechdbInfoAry = null;

 private Context mContext;

 MyApplication myApplication;
	
	
	
	//============================================================
 private TextView mTitleTv;
	
	public static Fragment01 newInstance() {
		Fragment01 mFragment01 = new Fragment01();
		return mFragment01;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.tabone, container,
//				false);
		
		this.mContext = ((MainActivity) getActivity()).getContext();
  View tab1 = inflater.inflate(R.layout.tabone, container, false);
  myApplication = (MyApplication) getActivity().getApplication();

  // ======================================================================
   AdManager.getInstance(this.mContext).init("e0e6d19ce2bd64c5",
   "14c3ce968c367cef", false);
   OffersManager.getInstance(this.mContext).onAppLaunch();
  // requestWindowFeature(Window.FEATURE_NO_TITLE);
  // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
  // WindowManager.LayoutParams.FLAG_FULLSCREEN);
  // setContentView(R.layout.activity_main);

  mDBManager = new DBManager(this.mContext);
  mTechdbInfoAry = new ArrayList<TechdbInfo>();
  mTechdbInfoAry = mDBManager.getEvent();
  mTextShowone = (TextView) tab1.findViewById(R.id.text_show_one);
  mTextShowone.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {

      }
  });
  mDataNum = mTechdbInfoAry.size();

  findView(tab1);

		
		
		
		return tab1;
	}
	
 private void findView(View view) {
     RelativeLayout adLayout = (RelativeLayout) view
             .findViewById(R.id.AdLayout);
//     adView = new AdView(mContext, AdSize.FIT_SCREEN);
//     adLayout.addView(adView);

//     initAdView();

//     mDictSelectBtn = (TextView) view
//             .findViewById(R.id.id_activity_main_dict);
//     mDictSelectBtn.setOnClickListener(new MyBtnListener());
//
//     mDictSelectLayout = (LinearLayout) view
//             .findViewById(R.id.id_activity_dict_layout);

//     mDictSelectBtn13 = (TextView) view
//             .findViewById(R.id.id_activity_dict_13);
//    mDictSelectBtn13.setOnClickListener(new MyBtnListener());
//     mDictSelectBtn35 = (TextView) view
//             .findViewById(R.id.id_activity_dict_35);
//     mDictSelectBtn35.setOnClickListener(new MyBtnListener());

     mBtnLeft = (Button) view.findViewById(R.id.id_activity_dict_left_word);
     mBtnLeft.setOnClickListener(new MyBtnListener());
     mBtnRight = (Button) view
             .findViewById(R.id.id_activity_dict_right_word);
     mBtnRight.setOnClickListener(new MyBtnListener());

     mTextWord = (TextView) view.findViewById(R.id.id_activity_dict_word);
     mTextSentence = (TextView) view
             .findViewById(R.id.id_activity_dict_sentence);

     
     
     mTitleTv = (TextView) view.findViewById(R.id.title_tv);
     mTitleTv.setText("儿童识字");
//     initWord();
 }
	
 class MyBtnListener implements OnClickListener {

     @Override
     public void onClick(View v) {
         // TODO Auto-generated method stub
         int iResid = v.getId();
         switch (iResid) {
         case R.id.text_show_one:
             nextWord();
             break;
//         case R.id.id_activity_main_dict:
//             setDictSelectLayoutVisiable();
//             break;
//         case R.id.id_activity_dict_13:
//             setDictSelectLayoutVisiable();
//             break;
//         case R.id.id_activity_dict_35:
//             setDictSelectLayoutVisiable();
//             break;
         case R.id.id_activity_dict_left_word:
             preWord();
             break;
         case R.id.id_activity_dict_right_word:
             nextWord();
             break;
         default:
             break;
         }

     }
 }
 
 private void preWord() {
     mIndex--;

     if (mIndex < 0) {
         mIndex = mDataNum - 1;
     }

     mTextShowone.setText(mTechdbInfoAry.get(mIndex).mZi);
     mTextWord.setText("单词：" + mTechdbInfoAry.get(mIndex).mCi);
     mTextSentence.setText("造句：" + mTechdbInfoAry.get(mIndex).mJu);
     myApplication.tts.stop();
     myApplication.tts.play(
             0,
             mTechdbInfoAry.get(mIndex).mZi + ","
                     + mTechdbInfoAry.get(mIndex).mCi + ","
                     + mTechdbInfoAry.get(mIndex).mJu);
     // tts.play(1, mTechdbInfoAry.get(mIndex).mCi);
     // tts.play(1, mTechdbInfoAry.get(mIndex).mJu);
 }
 
// private void showSpotAds() {
//     // 展示插播广告，可以不调用loadSpot独立使用
//     SpotManager.getInstance(this.mContext).showSpotAds(this.mContext,
//             new SpotDialogListener() {
//                 @Override
//                 public void onShowSuccess() {
//                     Log.i("YoumiAdDemo", "展示成功");
//                 }
//
//                 @Override
//                 public void onShowFailed() {
//                     Log.i("YoumiAdDemo", "展示失败");
//                 }
//
//             }); // //
//
// }
 private void takeRest() {
     android.content.DialogInterface.OnClickListener positiveBtnListener = new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
//             if (which == -1) {
//                 showSpotAds();
//             }
         }
     };
//     DialogTool.createConfirmDialog(this.mContext, "提示", "您已识字有一会了，是否休息一下？",
//             "退出", "继续学习", positiveBtnListener, positiveBtnListener,
//             R.drawable.icon).show();
//     
}

 private void nextWord() {
     mIndex++;
     mReadTotal++;
     if (mIndex >= mDataNum) {
         takeRest();
         mIndex = 0;
     }
     if (mReadTotal >= MAX_NUM) {
         mReadTotal = 0;
         takeRest();
     }

     mTextShowone.setText(mTechdbInfoAry.get(mIndex).mZi);
     mTextWord.setText("单词：" + mTechdbInfoAry.get(mIndex).mCi);
     mTextSentence.setText("造句：" + mTechdbInfoAry.get(mIndex).mJu);
     myApplication.tts.stop();
     myApplication.tts.play(
             0,
             mTechdbInfoAry.get(mIndex).mZi + ","
                     + mTechdbInfoAry.get(mIndex).mCi + ","
                     + mTechdbInfoAry.get(mIndex).mJu);
     // tts.play(1, mTechdbInfoAry.get(mIndex).mCi);
     // tts.play(1, mTechdbInfoAry.get(mIndex).mJu);
 }
// private void setDictSelectLayoutVisiable() {
//     if (mDictSelectLayout.getVisibility() == View.GONE) {
//         Animation anim = AnimationUtils.loadAnimation(this.mContext,
//                 R.anim.top_in);
//         mDictSelectLayout.setAnimation(anim);
//
//         mDictSelectLayout.setVisibility(View.VISIBLE);
//     } else {
//         Animation anim = AnimationUtils.loadAnimation(this.mContext,
//                 R.anim.top_out);
//         mDictSelectLayout.setAnimation(anim);
//
//         mDictSelectLayout.setVisibility(View.GONE);
//     }
// }
 
 
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initViews(View view) {
	    
	    
	    
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public String getFragmentName() {
		return TAG;
	}

}
