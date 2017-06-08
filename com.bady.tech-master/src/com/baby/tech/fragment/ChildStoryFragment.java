package com.baby.tech.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.baby.tech.R;
import com.baby.tech.activity.AudioActivity;
import com.baby.tech.activity.MainActivity;
import com.baby.tech.adapter.AudioMenuAdapter;
import com.baby.tech.adapter.QianbiyinAdapter;
import com.baby.tech.adapter.VedioMenuAdapter;
import com.baby.tech.base.MyApplication;
import com.baby.tech.db.Constant;
import com.baby.tech.entity.BaseEntity;
import com.baby.tech.entity.NetInfo;
import com.baby.tech.entity.NetInfoRsp;
import com.baby.tech.net.INetDataCallBack;
import com.baby.tech.net.NetSocket;
import com.baby.tech.tools.DownLoadBlock;
import com.baby.tech.utils.Util;

public class ChildStoryFragment extends BaseFragment  {
    
   ImageView mView  ;
   DownLoadBlock mBlock ;
   GridView vedioGridMenu;
   
   Context mContext ;
   MyApplication mCommonApplication;
	public static ChildVedioFragment newInstance() {
		ChildVedioFragment collectFragment = new ChildVedioFragment();

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
	    mContext= ((MainActivity) getActivity()).getContext();
	    mCommonApplication = (MyApplication) getActivity().getApplication();
     View view = inflater.inflate(R.layout.activity_qianbiyin, container, false);
 

		return view;
	}

 class btn_listen implements OnItemClickListener, INetDataCallBack {
     

     @Override
     public void onDataStart() {
     }

     @Override
     public void onDataFinish(BaseEntity entity) {
         
         try {
             if (entity == null || entity.data == null) {
                 return;
             }
             NetInfoRsp mStroyInfoRsp = new NetInfoRsp();
             JSONObject jsonObject = new JSONObject(
                     Util.unCompressByGzip(entity.data));
             int ret = jsonObject.getInt("ret");
             mStroyInfoRsp.setRet(ret);
             if (ret == 0) {
                 /*
                  * "ret": 0, "num": 20, "cmd": 10202, "infotype": "resinfo",
                  * "ResDataAry": [{ "resclass": "10001", "resname": "10001",
                  * "respath": "\/onestudy\/shizi_pic\/", "name": "鸡",
                  * "classname": "动物" }, { "resclass": "10001", "resname":
                  * "10002", "respath": "\/onestudy\/shizi_pic\/", "name":
                  * "鸭", "classname": "动物" },
                  */

                 int num = jsonObject.getInt("num");
                 int cmd = jsonObject.getInt("cmd");
                 String infotype = jsonObject.getString("infotype");
                 mStroyInfoRsp.setCmd(cmd);
                 mStroyInfoRsp.setNum(num);
                 mStroyInfoRsp.setInfotype(infotype);

                 JSONArray JsonResDataAry = jsonObject
                         .getJSONArray("ResDataAry");
                 //判断 多媒体 列表 集合 不为空 
                 
                 if(mCommonApplication.getNetInfoList()!=null
                         &&mCommonApplication.getNetInfoList().size()!=0){
                     //先清空多媒体 列表集合
                     mCommonApplication.getNetInfoList().clear();
                 }
                 List<NetInfo> netInfoList = new ArrayList<NetInfo>();
                 
                 for (int i = 0; i < JsonResDataAry.length(); i++) {
                     JSONObject jsonary = JsonResDataAry.getJSONObject(i);
                     NetInfo mStoryInfo = new NetInfo();
                     mStoryInfo.setResclass(jsonary.getString("resclass"));
                     mStoryInfo.setResname(jsonary.getString("resname"));
                     mStoryInfo.setRespath(jsonary.getString("respath"));
                     mStoryInfo.setName(jsonary.getString("name"));
                     mStoryInfo.setClassname(jsonary.getString("classname"));
                     netInfoList.add(mStoryInfo);
                 }
                 mCommonApplication.setNetInfoList(netInfoList);
                 // Util.setPrefString(context,
                 // Constant.PREF_AUTH_NAME, mstrUser);
                 Intent intent = null;
                 
                 intent = new Intent(mContext, AudioActivity.class);
                 startActivity(intent);
                

             } else {
                 Toast.makeText(mContext, "数据解析错误", Toast.LENGTH_LONG).show();
             }
         } catch (Exception e) {
             // throw new RuntimeException(e);
             e.printStackTrace();
         }
         
         
     }

     @Override
     public void onDataError() {
     }

     @Override
     public void onItemClick(AdapterView<?> arg0, View arg1, int position,
             long arg3) {
         switch (position) {
         case 0:
             //动物
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.an).start();
             NetSocket.SendData(this, NetSocket.registerAuio(Constant.CLASSIC_MUSIC));
             Toast.makeText(mContext, "an", Toast.LENGTH_SHORT).show();
             break;
         case 1:
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.en).start();
             NetSocket.SendData(this, NetSocket.registerAuio(Constant.HOTSONG_MUSIC));
             Toast.makeText(mContext, "en", Toast.LENGTH_SHORT).show();
             //植物
             break;
         case 2:
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.in).start();
             NetSocket.SendData(this, NetSocket.registerAuio(Constant.ENGLISH_MUSIC));

             Toast.makeText(mContext, "in", Toast.LENGTH_SHORT).show();
             //水果
             break;
         case 3:
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.un).start();
             NetSocket.SendData(this, NetSocket.registerAuio(Constant.BABYSONG_MUSIC));
             Toast.makeText(mContext, "un", Toast.LENGTH_SHORT).show();
             //蔬菜
             
             break;
         case 4:
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.yun).start();
             NetSocket.SendData(this, NetSocket.registerAuio(Constant.THREESONG_MUSIC));
             Toast.makeText(mContext, "ün", Toast.LENGTH_SHORT).show();
            //汽车
             break;
         case 5:
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.t).start();
             NetSocket.SendData(this, NetSocket.registerAuio(Constant.KINDERGARTENSONG_MUSIC));
             Toast.makeText(mContext, "t", Toast.LENGTH_SHORT).show();
             //实物
             break;

         default:
             break;
         }  
         
         
     }
     
 }
	
 private void initViews(View view) {
     
     vedioGridMenu = (GridView) view.findViewById(R.id.vedioGridMenu);

     QianbiyinAdapter mAdapter = new  QianbiyinAdapter(mContext);
     vedioGridMenu.setAdapter(mAdapter);
     //监听每个item的点击事件
     vedioGridMenu.setOnItemClickListener(new btn_listen());
     
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
        return null;
        	
    }

 


}



//package com.baby.tech.fragment;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.GridView;
//
//import com.baby.tech.R;
//import com.baby.tech.activity.MainActivity;
//import com.baby.tech.base.MyApplication;
//import com.baby.tech.db.Constant;
//
//public class ChildStoryFragment extends BaseFragment  {
//    
//   GridView audioGridMenu;
//   
//   Context mContext ;
//   MyApplication mCommonApplication;
//   
//   private WebView webView;
//   
//	public static ChildStoryFragment newInstance() {
//		ChildStoryFragment collectFragment = new ChildStoryFragment();
//
//		return collectFragment;
//	}
//
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		
//		
//		
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//	    mContext= ((MainActivity) getActivity()).getContext();
//	    mCommonApplication = (MyApplication) getActivity().getApplication();
//    View view = inflater.inflate(R.layout.activity_childstory, container, false);
//    initViews(view);
//    openBrowser();
//
//		return view;
//	}
//
//	
//	
// class webViewClient extends WebViewClient {
//     // 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
//     @Override
//     public boolean shouldOverrideUrlLoading(WebView view, String url) {
//         view.loadUrl(url);
//         // 如果不需要其他对点击链接事件的处理返回true，否则返回false
//         return true;
//     }
// }
//	
// 
//     
//
// // 利用webView的loadUrl方法
// public void openBrowser() {
//     // webView.loadUrl("http://192.168.1.104/map/weather.html");
//     // webView.loadUrl("http://www.baidu.com");
/////     webView.loadUrl(Constant.HTTP_SERVER_IP + "onestudy/edu/gushi.htm");
//
//     webView.setWebChromeClient(new WebChromeClient() {
//         @Override
//         public void onProgressChanged(WebView view, int newProgress) {
//             if (newProgress == 100) {
//                 // MainActivity.this.setTitle("加载完成");
//             } else {
//                 // MainActivity.this.setTitle("加载中.......");
//
//             }
//         }
//     });
//
// }
//	
// private void initViews(View view) {
//     
//     webView = (WebView) view.findViewById(R.id.Weather_webView);
//     webView.getSettings().setJavaScriptEnabled(true);
//     webView.setScrollBarStyle(0);
//     webView.setWebViewClient(new webViewClient());
//     WebSettings webSettings = webView.getSettings();
//     webSettings.setAllowFileAccess(true);
//     webSettings.setBuiltInZoomControls(true);
//     
//    }
// 
//	
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		initViews(view);
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//	 
//	}
//
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//	 if (webView.canGoBack()) {
//	     webView.goBack(); // goBack()表示返回webView的上一页面
//  }
//	}
//
//	
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
//	}
//
//    @Override
//    public String getFragmentName() {
//        return null;
//        	
//    }
//
// 
//
//
//}
