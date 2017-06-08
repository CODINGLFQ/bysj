package com.baby.tech.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baby.tech.R;
import com.baby.tech.entity.NetInfo;
import com.baby.tech.utils.Player;



@SuppressLint("HandlerLeak")
public class MultiMediaListAdapter extends BaseAdapter {
    List<String> group = new ArrayList<String>() ;
    LayoutInflater mInflater;
    Context context;

    //
    
    public  int paynum = 0 ;
    public  Player player;
    public int progress = 0;
    
    public MultiMediaListAdapter(Context context,List<NetInfo> netInfoList) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
        if(group!=null&&group.size()>0){
            group.clear();
        }
        for (int i = 0; i < netInfoList.size(); i++) {
            group.add(netInfoList.get(i).getName());
        }
        this.context = context;
        
        
    }

    @Override
    public int getCount() {
        return group.size();
        	
    }

    @Override
    public Object getItem(int arg0) {
        return null;
        	
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
        	
    }

    @Override
    public View getView(int arg0, View view, ViewGroup arg2) {
        TextView channel_group_name = null ;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.channel_expandablelistview, null); 
             channel_group_name = (TextView) view.findViewById(R.id.channel_group_name);
            String name = group.get(arg0);
            channel_group_name.setText(name);
            
            channel_group_name.setTextSize(15);
        }
//        if (convertView == null) {
//            mViewChild = new ViewChild();
//            convertView = mInflater.inflate(R.layout.channel_expandablelistview, null);
//            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
//            mViewChild.imageView = (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
//            convertView.setTag(mViewChild);
//        } else {
//            mViewChild = (ViewChild) convertView.getTag();
//        }
        
            
        try {
            if (arg0 == selectItem) 
            {  
                view.setBackgroundResource(R.drawable.content_panel_bglist5);
                channel_group_name = (TextView) view.findViewById(R.id.channel_group_name);
                String name = group.get(arg0);
                channel_group_name.setText(name);
                channel_group_name.getPaint().setFakeBoldText(true);
                channel_group_name.setTextSize(25);   //选中的Item字体：25px
            }   
            else 
            {  
              view.setBackgroundResource(R.drawable.content_panel_bglist4);
              channel_group_name = (TextView) view.findViewById(R.id.channel_group_name);
              String name = group.get(arg0);
              channel_group_name.setText(name);
              channel_group_name.setTextSize(15);   //未选中的Item字体：15px
              channel_group_name.getPaint().setFakeBoldText(false);
            }     
        }
           catch (Exception e) {
      e.printStackTrace();
     }
        
        return view;
        	
    }
    public void setSelectItem(int selectItem) { 
        
        
        this.selectItem = selectItem;  
   } 
    private int  selectItem= -1;  
}
