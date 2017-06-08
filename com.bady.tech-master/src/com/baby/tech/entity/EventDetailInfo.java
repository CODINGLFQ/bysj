package com.baby.tech.entity;

import java.io.Serializable;

/**
 * 事件详情
 * 
 * @author KF_LIU
 */
public class EventDetailInfo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public int mId = -1;

    public int mEvent = -1;
    /** 经度 */
    public double mLon = 0.0;
    /** 纬度 */
    public double mLat = 0.0;
    /** 定位时间 */
    public String mTime = "";
    /** 地址 */
    public String mAddr = "";
    /** 速度 */
    public int mSpeed = 0;
    /** 精度范围 */
    public int mRadius = -1;
    /** 方向 */
    public String mDirection = "";

    public String strUser = "";

    public String strSimID = "";

    // MESG
    public int mUid = -1;

    public int mFid = -1;

    public int mGid = -1;

    public int mRcode = -1;

    public String strUname = "";

    public String strFname = "";

    public String strGname = "";

    public boolean bNewInfo = false;

    public EventDetailInfo(double lon, double lat, String time, int speed,
            String direction) {
        mEvent = 0;
        mLon = lon;
        mLat = lat;
        mTime = time;
        mSpeed = speed;
        mDirection = direction;
        bNewInfo = false;
    }

    public EventDetailInfo() {

    }
}
