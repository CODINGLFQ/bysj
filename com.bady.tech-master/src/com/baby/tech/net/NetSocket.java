package com.baby.tech.net;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.baby.tech.R;
import com.baby.tech.base.MyApplication;
import com.baby.tech.db.Constant;
import com.baby.tech.entity.BaseEntity;
import com.baby.tech.entity.EventDetailInfo;

public class NetSocket {
    /** Called when the activity is first created. */

    public static String mstrSendData = "";
    // public String mstrRecvData = "";
    private static Handler mSendHandler = null;
    private static Handler mRecvHandler = null;
    public static String TAG = "LocByCar";
    private static final int REQCODE_CAMERA = 202;
    private static Context mContext = null;

    private static final int NET_START = 0;
    private static final int NET_FINISH = 1;
    private static final int NET_ERROR = -1;

    public NetSocket() {
        CreateHandler();
    }

    public void SetEventHandler(Handler handler, Context context) {
        mContext = context;
        mRecvHandler = handler;
    }

    public static void SendData(String strSendData) {
        if (false == NetStateHelpers.isNetworkAvailable(mContext)) {
//            Toast.makeText(mContext,
//                    mContext.getString(R.string.text_net_not_available),
//                    Toast.LENGTH_LONG).show();
            return;
        }
        mstrSendData = strSendData;
        SendEventMsg(1);
    }

    public static void SendData(final INetDataCallBack callback,
            final String strSendData) {
//        if (!NetStateHelpers.isNetworkAvailable(MyApplication.instance()
//                .getApplicationContext())) {
//            Toast.makeText(
//                    MyApplication.instance().getApplicationContext(),
//                    MyApplication.instance().getApplicationContext()
//                            .getString(R.string.text_net_not_available),
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }

        new Thread(new Runnable() {
            final Handler mHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                    case NET_START:
                        if (callback != null) {
                            callback.onDataStart();
                        }
                        break;
                    case NET_FINISH:
                        if (callback != null && msg.obj != null) {
                            BaseEntity entity = new BaseEntity();
                            entity.data = (byte[]) msg.obj;
                            callback.onDataFinish(entity);
                        }
                        break;
                    case NET_ERROR:
                        break;
                    default:
                        break;
                    }
                }
            };

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mHandler.sendMessage(mHandler.obtainMessage(NET_START));

                byte[] result = SendDataByThread(strSendData);

                mHandler.sendMessage(mHandler.obtainMessage(NET_FINISH, result));
            }
        }).start();
    }

    public static void SendEventMsg(int nWhat) {

        if (mSendHandler != null) {
            Message msg = mSendHandler.obtainMessage();
            msg.what = nWhat;
            mSendHandler.sendMessage(msg);
        }

    }

    private void RecvEventMsg(int nWhat) {
        if (mRecvHandler != null) {
            Message msg = mRecvHandler.obtainMessage();
            msg.what = nWhat;
            mRecvHandler.sendMessage(msg);
        }
    }

    void DespatchTask(String strTask) {
        try {
            JSONObject jsonObject = new JSONObject(strTask);
            int nRet = jsonObject.getInt("ret");
            if (nRet == 0) {
                int num = jsonObject.getInt("num");
                System.out.println("num: " + num);
                JSONArray jsonAry = jsonObject.getJSONArray("EventDataAry");
                for (int i = 0; i < num; i++) {
                    EventDetailInfo eventDetailInfo = new EventDetailInfo();
                    JSONObject jsonary = jsonAry.getJSONObject(i);
                    String strTime = jsonary.getString("time");// 时间格式的字符串
                    SimpleDateFormat formatter = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    Date date1 = null;
                    try {
                        date1 = formatter.parse(strTime);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (date1 != null) {
                        eventDetailInfo.mTime = "" + date1.getDate() + "日"
                                + date1.getHours() + ":" + date1.getMinutes();
                    } else {
                        eventDetailInfo.mTime = "";
                    }
                    eventDetailInfo.mAddr = jsonary.getString("addr");
                    eventDetailInfo.mLon = jsonary.getDouble("lon");
                    eventDetailInfo.mLat = jsonary.getDouble("lat");
                    eventDetailInfo.mRadius = jsonary.getInt("radius");

                    // System.out.println(strEvent);

                    // mGetEventList.add(strEvent);
                }
            } else if (nRet == 20) {
                int bUpdate = jsonObject.getInt("bupdate");
                if (bUpdate == 1) {
                    RecvEventMsg(3);
                } else {
                    RecvEventMsg(4);
                }
            } else if (nRet == 31) {
                RecvEventMsg(1);
            } else {
                SendEventMsg(2);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void CreateHandler() {
        mSendHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                case 1:
                    final Runnable initRunnable = new Runnable() {
                        public void run() {
                            // Looper.prepare();
                            SendDataByThread(mstrSendData);
                            // Looper.loop();
                        }
                    };
                    new Thread(initRunnable).start();
                    break;
                case 2:
                    // mTv.setText(mstrRecvData);
                    break;
                case 3:

                    break;

                default:
                    break;
                }
            }
        };
    }

    private static byte[] SendDataByThread(String readline) {
        try {
            Socket socket = new Socket();
///            SocketAddress address = new InetSocketAddress(Constant.SERVER_IP,
///                    Constant.SERVER_PORT);
            // 由系统标准输入设备构造BufferedReader对象
            // PrintWriter os = new PrintWriter(new OutputStreamWriter(
            // socket.getOutputStream(), "gb2312"));
            try {
///                socket.connect(address, 5000);
                socket.setSoTimeout(3000);

                PrintWriter os = new PrintWriter(new OutputStreamWriter(
                        socket.getOutputStream(), "utf8"));
                // 由Socket对象得到输入流，并构造相应的BufferedReader对象
                byte[] bRecv = new byte[1024];

                String strMsg = "";
                {
                    os.println(readline);
                    // 将从系统标准输入读入的字符串输出到Server
                    os.flush();
                    // 在系统标准输出上打印读入的字符串
                    int n = 0;

                    while ((n = socket.getInputStream().read(bRecv)) > 0) {
                        // strMsg = new String(b, "utf8");
                        // mstrRecvData += strMsg;
                        if (n < 1024) {
                            break;
                        }
                    }
                    // System.out.print("Server:" + mstrRecvData);
                    // mstrRecvData = Util.uncompress(b);
                    // DespatchTask(mstrRecvData);
                } // 继续循环
                os.close(); // 关闭Socket输出流
                socket.close(); // 关闭Socket

                return bRecv;

            } catch (Exception e) {
                socket.close(); // 关闭Socket
            }

        } catch (Exception e) {
            System.out.println("Error" + e);// 出错，则打印出错信息
        }

        return null;
    }
    
    
    
    /**
     * 
     * 功能描述：图片
     * 
     * @author <p>
     *         创建日期 ：2014-7-27 下午3:57:05
     *         </p>
     * 
     * @return
     * 
     *         <p>
     *         修改历史 ：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    public static String registerImg(final int imgType) {
        try {
            // {"infotype":"resinfo","cmd":202,"simid":"A0000049922077"}
            JSONObject JsonObject = new JSONObject();
            JsonObject.put("infotype", "resinfo");
            // 图片
            JsonObject.put("cmd", 202);
            JsonObject.put("resclass", imgType);
            JsonObject.put("simid", "A0000049922077");
            return JsonObject.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * 功能描述： 音频
     * 
     * @author <p>
     *         创建日期 ：2014-7-27 下午3:56:53
     *         </p>
     * 
     * @return
     * 
     *         <p>
     *         修改历史 ：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    public static String registerAuio(final int audioType) {
        try {
            // {"infotype":"resinfo","cmd":202,"simid":"A0000049922077"}
            JSONObject JsonObject = new JSONObject();
            JsonObject.put("infotype", "resinfo");
            // 音频
            JsonObject.put("cmd", 203);
            JsonObject.put("res", 203);
            JsonObject.put("resclass", audioType);
            JsonObject.put("simid", "A0000049922077");
            return JsonObject.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 
     * 功能描述：视频
     * 
     * @author <p>
     *         创建日期 ：2016-4-27 下午3:56:39
     *         </p>
     * 
     * @return
     * 
     *         <p>
     *         修改历史 ：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    public static String registerVedio() {
        try {
            // {"infotype":"resinfo","cmd":202,"simid":"A0000049922077"}
            JSONObject JsonObject = new JSONObject();
            JsonObject.put("infotype", "resinfo");
            // 视频
            JsonObject.put("cmd", 204);
            JsonObject.put("simid", "A0000049922077");
            return JsonObject.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
