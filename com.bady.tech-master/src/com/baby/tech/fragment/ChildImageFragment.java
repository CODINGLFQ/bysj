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
import com.baby.tech.activity.StudyImgActivity;
import com.baby.tech.adapter.AudioMenuAdapter;
import com.baby.tech.adapter.StudyImageMenuAdapter;
import com.baby.tech.base.MyApplication;
import com.baby.tech.db.Constant;
import com.baby.tech.entity.BaseEntity;
import com.baby.tech.entity.NetInfo;
import com.baby.tech.entity.NetInfoRsp;
import com.baby.tech.net.INetDataCallBack;
import com.baby.tech.net.NetSocket;
import com.baby.tech.tools.DownLoadBlock;
import com.baby.tech.utils.Util;

public class ChildImageFragment extends BaseFragment  {
    
   ImageView mView  ;
   DownLoadBlock mBlock ;
   GridView studyImagGridMenu;
   
   Context mContext ;
   MyApplication mCommonApplication;
	public static ChildImageFragment newInstance() {
		ChildImageFragment collectFragment = new ChildImageFragment();

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
    View view = inflater.inflate(R.layout.activity_study_imgmenu, container, false);
    initViews(view);
    
		return view;
	}

	
 private void initViews(View view) {
     
     
     studyImagGridMenu = (GridView)view.findViewById(R.id.studyImagGridMenu);

     StudyImageMenuAdapter mAdapter = new StudyImageMenuAdapter(mContext);
     studyImagGridMenu.setAdapter(mAdapter);
     //监听每个item的点击事件
     studyImagGridMenu.setOnItemClickListener(new btn_listen());
    
     
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
                 intent = new Intent(mContext, StudyImgActivity.class);
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
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.ang).start();
             NetSocket.SendData(this, NetSocket.registerImg(Constant.ANIMAIL_IMG));
             Toast.makeText(mContext, "ang", Toast.LENGTH_SHORT).show();
             break;
         case 1:
             //植物
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.eng).start();
             NetSocket.SendData(this, NetSocket.registerImg(Constant.PLANT_IMG));
             Toast.makeText(mContext, "eng", Toast.LENGTH_SHORT).show();
             break;
         case 2:
             //水果
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.ing).start();
             NetSocket.SendData(this, NetSocket.registerImg(Constant.FRUIT_IMG));
             Toast.makeText(mContext, "ing", Toast.LENGTH_SHORT).show();
             break;
         case 3:
             //蔬菜
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.ong).start();
             NetSocket.SendData(this, NetSocket.registerImg(Constant.VEGETABLE_IMG));
             Toast.makeText(mContext, "ong", Toast.LENGTH_SHORT).show();
             
             break;
         case 4:
             //汽车
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.b).start();
             NetSocket.SendData(this, NetSocket.registerImg(Constant.CAR_IMG));
             Toast.makeText(mContext, "汽车", Toast.LENGTH_SHORT).show();
             break;
         case 5:
             //食物
        	 MediaPlayer.create(getActivity().getApplicationContext(), R.raw.b).start();
             NetSocket.SendData(this, NetSocket.registerImg(Constant.OBJECT_IMG));
             Toast.makeText(mContext, "食物", Toast.LENGTH_SHORT).show();
             break;

         default:
             break;
         }  
         
         
     }
     
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
