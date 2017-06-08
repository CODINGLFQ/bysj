package com.baby.tech.net;

import com.baby.tech.entity.BaseEntity;

public interface INetDataCallBack {

    public void onDataStart();// 正在处理网络数据

    public void onDataFinish(BaseEntity entity);// 网络数据处理完毕

    public void onDataError();// 网络数据异常
}
