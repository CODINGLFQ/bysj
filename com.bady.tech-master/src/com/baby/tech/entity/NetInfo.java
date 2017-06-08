/**
 * NetInfo.java V1.0 2014-7-27 下午3:29:43
 *
 * Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By Time Reason):
 *
 * Description:
 */

package com.baby.tech.entity;

public class NetInfo {
    /*
     * "resclass": "10001", "resname": "10002", "respath":
     * "\/onestudy\/shizi_pic\/", "name": "鸭", "classname": "动物"
     */

    String resclass;
    String resname;
    String respath;
    String name;
    String classname;

    public String getResclass() {
        return resclass;
    }

    public void setResclass(String resclass) {
        this.resclass = resclass;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getRespath() {
        return respath;
    }

    public void setRespath(String respath) {
        this.respath = respath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

}
