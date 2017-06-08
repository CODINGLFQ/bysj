package com.baby.tech.net;

/**
 * HTTP代理类
 * 
 * @author panyoufu
 */
public class MyProxy {

    private String mProxy = null;

    private int mPort = 0;

    private static MyProxy INSTANCE = null;

    /**
     * 获取代理实例
     * 
     * @return 代理实例
     */
    public static MyProxy getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new MyProxy();
        }

        return INSTANCE;
    }

    /**
     * 设置代理地址
     * 
     * @param proxy
     *            代理地址
     * 
     * @return
     */
    public void setProxy(String proxy) {
        mProxy = proxy;
    }

    /**
     * 设置端口
     * 
     * @param port
     *            端口
     * 
     * @return
     */
    public void setPort(int port) {
        mPort = port;
    }

    /**
     * 获取代理地址
     * 
     * @return 代理地址
     */
    public String getProxy() {
        return mProxy;
    }

    /**
     * 获取端口
     * 
     * @return 端口号
     */
    public int getPort() {
        return mPort;
    }
}
