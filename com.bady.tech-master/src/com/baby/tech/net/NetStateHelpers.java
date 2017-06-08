package com.baby.tech.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Handler;
import android.os.Message;

import com.baby.tech.base.AlertTips;
import com.baby.tech.db.ResultCode;

/**
 * 终端网络状态辅助类
 * 
 * @author panyoufu
 */
public class NetStateHelpers {

    private static final String TAG = "NetStateHelpers";

    private static NetStateHelpers mNetHelper = null;

    private static Context mContext = null;

    private static String mProxyAddr = "";

    private static int mProxyPort = 0;

    private static final int SERVICE_MSG_TYPE_NET_AVAILABLE = 0x10000001;

    private static final int SERVICE_MSG_TYPE_NET_NOT_AVAILABLE = 0x10000002;

    private static Handler mNetStateHandler = null;

    private InnerBroadcastReceiver mReceiver = null;

    public static boolean mNetState = true;

    /**
     * 获取实例
     * 
     * @param context
     *            当前的Context
     * 
     * @return 实例
     */
    public static NetStateHelpers getInstance() {
        if (null == mNetHelper) {
            mNetHelper = new NetStateHelpers();
            mNetStateHandler = new NetStateHandler();
        }
        return mNetHelper;
    }

    /**
     * 监听网络状态
     * 
     * 
     * @return
     */
    public void listen(Context ctx) {
        if (null != mReceiver) {
            unListen();
        }
        mContext = ctx;
        mReceiver = new InnerBroadcastReceiver();
        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(mReceiver, filter);
    }

    /**
     * 取消监听网络状态
     * 
     * 
     * @return
     */
    public void unListen() {
        if (null == mReceiver || null == mContext) {
            return;
        }
        mContext.unregisterReceiver(mReceiver);
        mReceiver = null;
    }

    /**
     * 获取网络状态
     * 
     * @param
     * 
     * @return 网络状态
     */
    public boolean getNetState() {
        return mNetState;
    }

    /**
     * 判断GPRS网络是否可用
     * 
     * @param context
     *            当前的Context
     * 
     * @return 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean rst = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            rst = false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        // MyLog.v(TAG,
                        // info[i].getTypeName() + "["
                        // + info[i].getSubtypeName()
                        // + "] is connected");
                        rst = true;
                        break;
                    }
                }
            }
        }

        return rst;
    }

    /**
     * Returns 判断WIFI是否可用
     * 
     * @param context
     *            当前的Context
     * 
     * @return WIFI是否可用
     */
    public static boolean isWifiAvailable(Context context) {
        boolean rst = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            rst = false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        // MyLog.v(TAG,
                        // info[i].getTypeName() + "["
                        // + info[i].getSubtypeName()
                        // + "] is connected");
                        if (info[i].getType() == ConnectivityManager.TYPE_WIFI) {
                            rst = true;
                            break;
                        }
                    }
                }
            }
        }
        // MyLog.v(TAG, "rst = " + rst);
        return rst;
    }

    private static class NetStateHandler extends Handler {
        public NetStateHandler() {
            super();
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SERVICE_MSG_TYPE_NET_AVAILABLE:
                synchronized (NetStateHandler.this) {
                    if (false == mNetState) {
                        AlertTips.notify(mContext,
                                ResultCode.NETWORK_SERVICE_AVAILABLE);
                    }
                    mNetState = true;
                    // MyLog.d(TAG, "SERVICE_MSG_TYPE_NET_AVAILABLE");
                    if (Proxy.getDefaultHost() != null) {
                        mProxyAddr = Proxy.getDefaultHost();
                        mProxyPort = Proxy.getDefaultPort();
                    } else {
                        mProxyAddr = "";
                        mProxyPort = 0;
                    }
                }
                break;
            case SERVICE_MSG_TYPE_NET_NOT_AVAILABLE:
                mNetState = false;
                // MyLog.d(TAG, "SERVICE_MSG_TYPE_NET_NOT_AVAILABLE");
                mProxyAddr = "";
                mProxyPort = 0;
                AlertTips.notify(mContext,
                        ResultCode.NETWORK_SERVICE_UNAVAILABLE);
                break;
            default:
                mProxyAddr = "";
                mProxyPort = 0;
                break;
            }

            MyProxy.getInstance().setProxy(mProxyAddr);
            MyProxy.getInstance().setPort(mProxyPort);
        }
    }

    private class InnerBroadcastReceiver extends BroadcastReceiver {
        public InnerBroadcastReceiver() {
            super();
        }

        public void onReceive(Context context, Intent intent) {
            if ((null == intent) || (null == context)) {
                return;
            }

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent
                    .getAction())) {

                boolean noConnectivity = intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

                if (false == noConnectivity) {
                    mNetStateHandler
                            .sendEmptyMessage(SERVICE_MSG_TYPE_NET_AVAILABLE);
                } else {
                    mNetStateHandler
                            .sendEmptyMessage(SERVICE_MSG_TYPE_NET_NOT_AVAILABLE);
                }
            }

        }
    }
}
