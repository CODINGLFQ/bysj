package com.baby.tech.base;

import java.util.List;

import net.youmi.android.dev.AppUpdateInfo;
import android.app.Application;
import android.content.SharedPreferences;

import com.baby.tech.entity.NetInfo;
import com.palmcity.tts.NaviTTS;

public class MyApplication extends Application {
    String smscurrenttime;
    public static MyApplication app;
    public SharedPreferences sharedPrefs;// 轻量级的存储类
    // 开始时间（统计软件使用时长时使用）
    public String mbeginTime = "0";
    //多媒体 文件 二级菜单的 列表
    List<NetInfo> netInfoList ;

    public String getSmscurrenttime() {
        return smscurrenttime;
    }

    public void setSmscurrenttime(String smscurrenttime) {
        this.smscurrenttime = smscurrenttime;
    }

    public static MyApplication getApp() {
        return app;
    }

    public static void setApp(MyApplication app) {
        MyApplication.app = app;
    }

    public SharedPreferences getSharedPrefs() {
        return sharedPrefs;
    }

    public void setSharedPrefs(SharedPreferences sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }

    public String getMbeginTime() {
        return mbeginTime;
    }

    public void setMbeginTime(String mbeginTime) {
        this.mbeginTime = mbeginTime;
    }

    public List<NetInfo> getNetInfoList() {
        return netInfoList;
    }

    public void setNetInfoList(List<NetInfo> storyInfoList) {
        this.netInfoList = storyInfoList;
    }

    public NaviTTS getTts() {
        return tts;
    }

    public void setTts(NaviTTS tts) {
        this.tts = tts;
    }

    public AppUpdateInfo getmAppUpdateInfo() {
        return mAppUpdateInfo;
    }

    public void setmAppUpdateInfo(AppUpdateInfo mAppUpdateInfo) {
        this.mAppUpdateInfo = mAppUpdateInfo;
    }

    public boolean ismIsBack() {
        return mIsBack;
    }

    public void setmIsBack(boolean mIsBack) {
        this.mIsBack = mIsBack;
    }

    /** 语音播报 **/
    public NaviTTS tts = null;

    // app update info
    public AppUpdateInfo mAppUpdateInfo = new AppUpdateInfo();
    // user login info
    // terminal info

    // 判断是否是返回键
    public boolean mIsBack = false;

    public MyApplication() {
        tts = new NaviTTS(this);
        app = this;
    }

    public static MyApplication instance() {
        return app;
    }

    public long getCurrentTime() {
        // TODO Auto-generated method stub
        long data = System.currentTimeMillis();
        return data;
    }

}
