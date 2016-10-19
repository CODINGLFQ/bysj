package com.baby.tech.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baby.tech.R;
import com.baby.tech.activity.base.BaseActivity;
import com.baby.tech.db.Constant;
import com.baby.tech.entity.NetInfo;
import com.baby.tech.tools.DownLoadBlock;

public class StudyImgActivity extends BaseActivity{
    private TextView mTitleTv;
    
   Button up_img ;
   Button next_img ;
   int studyimg_Index = 0 ;
   Context context ;
   TextView info_img ;
   ImageView mView  ;
   DownLoadBlock mBlock ;
   List<NetInfo> netInfoList = new ArrayList<NetInfo>(); 
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this ;
        setContentView(R.layout.activity_study_img);
        initViews();
        
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        info_img = (TextView) findViewById(R.id.info_img);
        netInfoList = mCommonApplication.getNetInfoList();
        mView  = (ImageView) findViewById(R.id.study_img);
        up_img = (Button) findViewById(R.id.up_img);
        next_img = (Button) findViewById(R.id.next_img);
        next_img.setOnClickListener(new btn_listern() );
        up_img.setOnClickListener(new btn_listern() );
        
        mBlock = new DownLoadBlock(this);
        
       // http://tileni.transmap.com.cn/8/103/207.png
//        String url = "http://tileni.transmap.com.cn/8/103/207.png";
///       String url = "http://"+Constant.SERVER_IPIMG
///                +netInfoList.get(0).getRespath()+netInfoList.get(0).getResname()+".jpg";
///        Bitmap mBitmap = mBlock.getBitmap(url);
        info_img.setText(netInfoList.get(0).getName());
///        mView.setBackgroundDrawable( new BitmapDrawable(mBitmap));
        
///        mView.setBackgroundDrawable( new BitmapDrawable(mBitmap));
    
        
        
        
        
    }

    
    private void initViews() {
        mTitleTv = (TextView)findViewById(R.id.title_tv);
        mTitleTv.setText("看图识字");
       }
    
    @SuppressLint("NewApi")
    class btn_listern implements OnClickListener{

        @Override
        public void onClick(View arg0) {
            String url = "" ;
            switch (arg0.getId()) {
            case R.id.up_img:
                if(studyimg_Index>0){
                studyimg_Index -- ;
                Toast.makeText(context, "上一张", Toast.LENGTH_LONG).show();
///                url = "http://"+Constant.SERVER_IPIMG
///                        +netInfoList.get(studyimg_Index).getRespath()+netInfoList.get(studyimg_Index).getResname()+".jpg";
                Bitmap mBitmap = mBlock.getBitmap(url);
                info_img.setText(netInfoList.get(studyimg_Index).getName());
                mView.setBackgroundDrawable( new BitmapDrawable(mBitmap));
                
                }
                else{
                    Toast.makeText(context, "上一张没有了", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.next_img:
                if(studyimg_Index<netInfoList.size()-1){
                    studyimg_Index ++ ;
                    Toast.makeText(context, "下一张", Toast.LENGTH_LONG).show();
///                    url ="http://"+ Constant.SERVER_IPIMG
///                            +netInfoList.get(studyimg_Index).getRespath()+netInfoList.get(studyimg_Index).getResname()+".jpg";
                    Bitmap mBitmap = mBlock.getBitmap(url);
                    info_img.setText(netInfoList.get(studyimg_Index).getName());
                    mView.setBackgroundDrawable( new BitmapDrawable(mBitmap));
                }
                    else{
                        Toast.makeText(context, "下一张没有了", Toast.LENGTH_LONG).show();
                    }
                break;

            default:
                break;
            }
        }
        
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.study_img, menu);
        return true;
    }
    
    
    

}
