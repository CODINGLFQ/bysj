package com.baby.tech.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.baby.tech.R;
import com.baby.tech.activity.base.BaseActivity;
import com.baby.tech.adapter.MultiMediaListAdapter;
import com.baby.tech.db.Constant;
import com.baby.tech.utils.Player;

public class AudioActivity extends BaseActivity {
    ListView mExpandableListView;
    MultiMediaListAdapter mExpandableListViewAdapter;

    private TextView mTitleTv;
    
    private Handler handler = new UIHandler();
    //====================================
    private static final int PLAY = 1000;
    private static final int PAUSE =1001;
    private ImageButton playBtn;
    private int paynum = 0 ;
    private SeekBar musicProgress;
    private Player player;
    String musicUrl = new String();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audiomain);
        
        initViews();
        mExpandableListView = (ListView) findViewById(R.id.expandableListView);
        
        mExpandableListViewAdapter = new MultiMediaListAdapter(this,mCommonApplication.getNetInfoList());
        mExpandableListView.setAdapter(mExpandableListViewAdapter);
        ButtonClickListener listener = new ButtonClickListener();
        playBtn.setOnClickListener(listener);
        musicProgress = (SeekBar) findViewById(R.id.music_progress);
        player = new Player(musicProgress);
        musicProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
     
        mExpandableListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                //http://www.lbs007.net:8087/onestudy/mp3/apple.mp3
//                String headUrl = Constant.SERVER_IPIMG ;
                String respath = mCommonApplication.getNetInfoList().get(arg2).getRespath();
                String resname = mCommonApplication.getNetInfoList().get(arg2).getResname();
//                musicUrl ="http://"+ headUrl+respath+resname+".mp3" ;
                paynum = 0 ;
                mExpandableListViewAdapter .setSelectItem(arg2);
                mExpandableListViewAdapter.notifyDataSetInvalidated(); // 通知adapter刷新数据
                handler.sendEmptyMessage(PAUSE);
                
                
            }
        });
        
//        mExpandableListView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//             public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//                 for(int i=0;i<parent.getCount();i++){
//                     View v=parent.getChildAt(parent.getCount()-1-i);
//                     if (position == i) {
//                         v.setBackgroundColor(Color.RED);
//                     } else {
//                         v.setBackgroundColor(Color.TRANSPARENT);
//                     }
//                 }
//             }
//         });
        
    }
  
    
    @Override
    protected void onStop() {
        super.onStop();
           	
    }
   
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player!=null){
        player.stop();
        }
    }
    
    private void initViews() {
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mTitleTv.setText("儿童音乐");
        playBtn = (ImageButton) findViewById(R.id.btn_online_play);
       }

    private final class UIHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
            
        case PLAY: // 暂停
           
            playBtn.setBackgroundResource(R.drawable.play);
            
            new Thread(new Runnable() {

                @Override
                public void run() {
                    player.pause();
                    paynum++;
                }
            }).start();
            
            
            break;
        
        case PAUSE: // 正在播放
            playBtn.setBackgroundResource(R.drawable.pause);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    if(paynum==0){
                    player.playUrl(musicUrl);
                    paynum++;
                    
                    }else{
                        player.play();
                        paynum++;
                    }
                    
                }
            }).start();
            
        break;
    }
        }
    } 

    
    private final class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.btn_online_play:
                
                if(paynum%2==0){
                    handler.sendEmptyMessage(PAUSE);
                  
                   }else{
                   
                       handler.sendEmptyMessage(PLAY);
                
//                   player.stop();
                   }
                
           
                break;
            }
        } 
    }
    
    class SeekBarChangeEvent implements OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }

    }
}
