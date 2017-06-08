/**
 * NetInfoRsp.java V1.0 2014-7-27 下午3:36:44
 *
 * Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By Time Reason):
 *
 * Description:
 */

package com.baby.tech.entity;

import java.util.ArrayList;
import java.util.List;

public class NetInfoRsp {
    /*
     * "ret": 0, "num": 20, "cmd": 10202, "infotype": "resinfo",
     */
    List<NetInfo> storyInfoList = new ArrayList<NetInfo>();
    int ret;

    public List<NetInfo> getStoryInfoList() {
        return storyInfoList;
    }

    public void setStoryInfoList(List<NetInfo> storyInfoList) {
        this.storyInfoList = storyInfoList;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    int num;
    int cmd;
    String infotype;

}
