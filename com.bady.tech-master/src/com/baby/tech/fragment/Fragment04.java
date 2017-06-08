package com.baby.tech.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baby.tech.R;
import com.baby.tech.activity.MainActivity;
import com.baby.tech.base.MyApplication;
import com.baby.tech.entity.BaseEntity;
import com.baby.tech.login.UpdateLoginActivity;
import com.baby.tech.net.INetDataCallBack;

public class Fragment04 extends BaseFragment {

	private static final String TAG = "CollectFragment";
	private TextView mTitleTv;
	//==============================================================
 TextView textView;
 private Context context;
 MyApplication myApplication;
 //==============================================================
	public static Fragment04 newInstance() {
		Fragment04 collectFragment = new Fragment04();

		return collectFragment;
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
		View view = inflater.inflate(R.layout.tabfour, container,
				false);
		
	 context = ((MainActivity) getActivity()).getContext();

  // sharebnt
  RelativeLayout sharebnt = (RelativeLayout) view
          .findViewById(R.id.sharebnt);
//  RelativeLayout updatebtn = (RelativeLayout) view
//          .findViewById(R.id.updatebtn);
//  RelativeLayout aboutbtn = (RelativeLayout) view
//          .findViewById(R.id.aboutbtn);
  RelativeLayout settingbnt = (RelativeLayout) view
          .findViewById(R.id.settingbnt);

  sharebnt.setOnClickListener(new btn_listen());
//  updatebtn.setOnClickListener(new btn_listen());
//  aboutbtn.setOnClickListener(new btn_listen());
  settingbnt.setOnClickListener(new btn_listen());
  myApplication = (MyApplication) getActivity().getApplication();
		
		return view;
	}

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
		mTitleTv = (TextView) view.findViewById(R.id.title_tv);
		mTitleTv.setText("更多");
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

 class btn_listen implements OnClickListener, INetDataCallBack {

     @Override
     public void onClick(View arg0) {
         switch (arg0.getId()) {
         case R.id.sharebnt:
             Intent it = new Intent(Intent.ACTION_SEND);
             it.putExtra(Intent.EXTRA_TEXT,
                     getResources().getString(R.string.share_content));
             it.setType("text/plain");
             startActivity(Intent.createChooser(it, "将儿童识字应用分享到"));
             break;
//         case R.id.updatebtn:
//             //
//             Toast.makeText(context, "版本更新", Toast.LENGTH_SHORT).show();
//             break ;
//         case R.id.aboutbtn:
//             Toast.makeText(context, "关于我们", Toast.LENGTH_SHORT).show();
//             break ;
         case R.id.settingbnt:
//             Toast.makeText(context, "设置", Toast.LENGTH_SHORT).show();
//             break ;
				Intent intent = new Intent();
				intent.setClass(getActivity().getApplicationContext(), UpdateLoginActivity.class);
				// intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
//				finish();

         default:
             break;
         }
     }

     @Override
     public void onDataFinish(BaseEntity entity) {
     }

     @Override
     public void onDataStart() {
     }

     @Override
     public void onDataError() {
     }

 }
}
