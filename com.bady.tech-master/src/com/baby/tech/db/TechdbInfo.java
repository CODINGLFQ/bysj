package com.baby.tech.db;

import java.io.Serializable;

/**
 * 朋友圈详情
 * 
 * @author KF_LIU
 */
public class TechdbInfo implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    public int mId;
    /** 好友ID */
    public String mZi;
    /** 好友名称 */
    public String mCi;
    /** 好友手机号 */
    public String mJu;

    public TechdbInfo() {

    }
}
