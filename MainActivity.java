package com.baby.tech.activity;

import net.youmi.android.offers.OffersManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.baby.tech.DialogTool;
import com.baby.tech.R;
import com.baby.tech.fragment.Fragment01;
import com.baby.tech.fragment.Fragment03;
import com.baby.tech.fragment.Fragment04;
import com.baby.tech.fragment.HomeFragment;
import com.baby.tech.utils.ConstantValues;
import com.baby.tech.view.MyTabWidget;
import com.baby.tech.view.MyTabWidget.OnTabSelectedListener;

/**
 * 导航页
 * 
 * @author dewyze
 * 
 */
public class MainActivity extends FragmentActivity implements
		OnTabSelectedListener {

 public Context context ;
 private static final String TAG = "MainActivity";
	private MyTabWidget mTabWidget;
	private Fragment01 mHomeFragment;
//	private Fragment02 mCategoryFragment;
	private HomeFragment mCategoryFragment;
	private Fragment03 mCollectFragment;
	private Fragment04 mSettingFragment;
	private int mIndex = ConstantValues.HOME_FRAGMENT_INDEX;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maintm);
		context = this ;
		init();
		initEvents();
	}

	private void init() {
		mFragmentManager = getSupportFragmentManager();
		mTabWidget = (MyTabWidget) findViewById(R.id.tab_widget);
	}

	private void initEvents() {
		mTabWidget.setOnTabSelectedListener(this); 
	}

	@Override
	protected void onResume() {
		super.onResume();
		onTabSelected(mIndex);
		mTabWidget.setTabsDisplay(this, mIndex);
		mTabWidget.setIndicateDisplay(this, 3, true);
	}

	@Override
	public void onTabSelected(int index) {
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (index) {
		case ConstantValues.HOME_FRAGMENT_INDEX:
			if (null == mHomeFragment) {
				mHomeFragment = new Fragment01();
				transaction.add(R.id.center_layout, mHomeFragment);
			} else {
				transaction.show(mHomeFragment);
			}
			break;
		case ConstantValues.CATEGORY_FRAGMENT_INDEX:
			if (null == mCategoryFragment) {
//				mCategoryFragment = new Fragment02();
				mCategoryFragment = new HomeFragment();
				transaction.add(R.id.center_layout, mCategoryFragment);
			} else {
				transaction.show(mCategoryFragment);
			}
			break;
		case ConstantValues.COLLECT_FRAGMENT_INDEX:
			if (null == mCollectFragment) {
				mCollectFragment = new Fragment03();
				transaction.add(R.id.center_layout, mCollectFragment);
			} else {
				transaction.show(mCollectFragment);
			}
			break;
		case ConstantValues.SETTING_FRAGMENT_INDEX:
			if (null == mSettingFragment) {
				mSettingFragment = new Fragment04();
				transaction.add(R.id.center_layout, mSettingFragment);
			} else {
				transaction.show(mSettingFragment);
			}
			break;

		default:
			break;
		}
		mIndex = index;
		transaction.commitAllowingStateLoss();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (null != mHomeFragment) {
			transaction.hide(mHomeFragment);
		}
		if (null != mCategoryFragment) {
			transaction.hide(mCategoryFragment);
		}
		if (null != mCollectFragment) {
			transaction.hide(mCollectFragment);
		}
		if (null != mSettingFragment) {
			transaction.hide(mSettingFragment);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// 自己记录fragment的位置,防止activity被系统回收时，fragment错乱的问题
		// super.onSaveInstanceState(outState);
		outState.putInt("index", mIndex);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// super.onRestoreInstanceState(savedInstanceState);
		mIndex = savedInstanceState.getInt("index");
	}
 public Context getContext() {
     return context;
 }

 public void setContext(Context context) {
     this.context = context;
 }
 
 @Override
 public boolean onKeyDown(int keyCode, KeyEvent event) {
     if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
             && event.getAction() == KeyEvent.ACTION_DOWN) {
         android.content.DialogInterface.OnClickListener positiveBtnListener = new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
                 if (which == -1) {
                     // startActivity(new Intent(CarTabActivity.this,
                     // BaseRegisterActivity.class));
                     //在应用退出的地方（如Activity的onDestroy方法中）调用以下代码进行资源回收
                     OffersManager.getInstance(context).onAppExit();
                     finish();
                 }
             }
         };
         DialogTool.createConfirmDialog(this, "提示", "确定退出程序？", "退出", "稍后退出",
                 positiveBtnListener, positiveBtnListener, R.drawable.icon)
                 .show();
         return true;
     }
     return false;
 }

}
