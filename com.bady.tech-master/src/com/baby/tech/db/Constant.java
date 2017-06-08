package com.baby.tech.db;

import android.util.Config;

/**
 * 常量类
 * 
 * @author panyoufu
 */
public class Constant {

    // ~ Static fields/initializers
    // ---------------------------------------------

    /** LogV的开关变量 */
    public static final boolean DEBUGV = Config.DEBUG;
    /** LogD的开关变量 */
    // public static final boolean DEBUGD = Log.isLoggable(CotaLog.TAG,
    // Log.DEBUG);
    public static final boolean DEBUGD = true;
    /** LogI的开关变量 */
    public static final boolean DEBUGI = true;
    /** LogW的开关变量 */
    public static final boolean DEBUGW = true;

    /** 缺省的String型值 */
    public static final String DEFAULT_STRING = null;

    /** 缺省的INT型值 */
    public static final int DEFAULT_INT = -1;
    /** 缺省的INT型值 */
    public static final float DEFAULT_DOUBLE = 0.0f;

    // JSON FLAG
    /** JSON字符串错误码类型标识 */
    public static final String JSON_ERROR_KEY = "ERRORCODE";

    /** JSON字符串CMMAND类型标识 */
    public static final String JSON_TYPE_KEY = "COMMTYPE";

    /** JSON字符串指令KEY */
    public static final String JSON_CMD_KEY = "REQUESTCODE";

    /** JSON字符串X */
    public static final String JSON_COORD_X_KEY = "X";

    /** JSON字符串Y */
    public static final String JSON_COORD_Y_KEY = "Y";

    /** JSON字符串小写x */
    public static final String JSON_COORD_CT_X_KEY = "x";

    /** JSON字符串小写y */
    public static final String JSON_COORD_CT_Y_KEY = "y";

    /** ID JSON KEY */
    public static final String JSON_ID_KEY = "ID";

    /** PASSWORD JSON KEY */
    public static final String JSON_PWD_KEY = "PWD";

    /** 回传的 业务类JSON KEY */
    public static final String JSON_RESP_TYPE_KEY = "TYPE";

    /** IMSI JSON KEY */
    public static final String JSON_IMSI_KEY = "SIMID";

    /** 定位时间 JSON KEY */
    public static final String JSON_POSTIME_KEY = "GPSTIME";

    /** 速度 JSON KEY */
    public static final String JSON_SPEED_KEY = "SPEED";

    /** 方向 JSON KEY */
    public static final String JSON_DIR_KEY = "DIRET";

    /** 温度 JSON KEY */
    public static final String JSON_TEMPERATURE_KEY = "temperature";

    /** 数量 JSON KEY */
    public static final String JSON_NUM_KEY = "ICOUNT";

    // Event code

    /** 请求处理完毕事件 */
    public static final int EVENT_REQUEST_OK = 0x10000000;

    // Entity type

    /** 认证实体类型 */
    public static final int ENTITY_TYPE_AUTH = 0x20000000;

    /** 坐标转换实体类型 */
    public static final int ENTITY_TYPE_COORD = 0x20000001;

    /** 车辆信息列表请求实体类型 */
    public static final int ENTITY_TYPE_CARLIST_INFO = 0x20000010;

    /** 点名查看请求实体类型 */
    public static final int ENTITY_TYPE_CAR_DETAIL_INFO = 0x20000011;

    /** 轨迹回放请求实体类型 */
    public static final int ENTITY_TYPE_TRACK_PLAYBACK = 0x20000101;

    // request intent content

    /** Service内部使用消息类型:请求处理结束 */
    public static final int MSG_SERVICE_REQ_COMPLETED = 0x30000001;

    /** Service处理的intent中的请求Entity */
    public static final String INTENT_REQ_ENTITY = "com.tztf.entity.req";

    /** Service处理的请求完成后广播的intent中的Entity */
    public static final String INTENT_RESP_ENTITY = "com.tztf.entity.resp";

    // preference name & attributes
    /** 账户信息perference文件名 */
    public static final String HLBREFERENCE = "com.hlb.auth.preference";

    /** Preference字段名：用户名 */
    public static final String PREF_AUTH_NAME = "hlb.pref.auth.name";

    /** Preference字段名：用户名 */
    public static final String PREF_AUTH_TEL = "hlb.pref.auth.tel";

    /** Preference字段名：用户账号 */
    public static final String PREF_AUTH_NUMBER = "hlb.pref.auth.number";

    /** Preference字段名：密码 */
    public static final String PREF_AUTH_PWD = "hlb.pref.auth.pwd";

    /** Preference字段名：MYPOS纬度 */
    public static final String PREF_MYPOS_LAT = "hlb.pref.mypos.lat";
    /** Preference字段名：MYPOS经度 */
    public static final String PREF_MYPOS_LON = "hlb.pref.mypos.lon";

    // request
    /** 缺省的请求超时时间 */
    public static final int DEFAULT_SOCKET_TIMEOUT = 3000;

    /** 缺省的连接超时时间 */
    public static final int DEFAULT_CONN_TIMEOUT = 3000;

    /** 缺省的请求时间间隔 */
    public static final int DEFAULT_REQ_INTERVAL = 3000;

    /** 缺省的请求重试次数 */
    public static final int DEFAULT_REQ_RETRY_TIMES = 3;

    /** 坐标转换请求时间间隔 */
    public static final int DEFAULT_COORD_REQ_INTERVAL = 2000;

    /** 坐标转换类型的请求重试次数 */
    public static final int DEFAULT_COORD_REQ_RETRY_TIMES = 2;

    /** 请求的Alarm类型 */
    public static final int ALARM_TYPE_REQUEST = 0xA1;

    /** 第一次请求的Action */
    public static final String ACTION_REQUEST = "com.tztf.intent.action.REQUEST";

    /** 重试请求的Action */
    public static final String ACTION_RETRYREQUEST = "com.tztf.intent.action.RETRYREQUEST";

    /** 请求完成的Action */
    public static final String ACTION_REQUEST_COMPLETED = "com.tztf.intent.action.REQUEST_COMPLETE";

    /** 重试请求的Extra：请求重试的次数 */
    public static final String EXTRA_REQ_TIMES = "com.tztf.intent.extra.REQTIMES";

    public static final String APK_DOWNLOAD_URL = "http://hao.i6188.com/LocByCar.apk";

    /** 1024的常量 */
    public static final int ONEKILO = 1024;

    /** 2048的常量 */
    public static final int TWOKILO = 2048;

    public static final int MIN_POS_LEN = 500;// 8818; //

    public static final int SERVER_PORT = 9007;// 8818; //

//    public static final String HTTP_SERVER_IP = "http://www.lbs007.net:8087/";

    // public static final String LOCALSERVER_IP =
    // "192.168.1.106";//"113.10.137.209";//www.i6188.com
//    public static final String SERVER_IP = "www.lbs007.net";// "113.10.137.209";//www.i6188.com
//    public static final String SERVER_IPIMG = "www.lbs007.net:8087";// "113.10.137.209";//www.i6188.com


    //看图识字 模块 图片的类型
    //1：动物
    public static final int ANIMAIL_IMG =10001 ;
    //2：植物
    public static final int PLANT_IMG =20002 ;
   //3：水果
   public static final int FRUIT_IMG =20005 ;
   //4：蔬菜
   public static final int VEGETABLE_IMG =20001 ;
   //5：汽车
   public static final int CAR_IMG =50001 ;
   //6：食物
   public static final int OBJECT_IMG =20003 ;
   
   // 模块 音乐的类型
   //1：经典儿歌
   public static final int CLASSIC_MUSIC =10001 ;
   //热门歌曲
   public static final int HOTSONG_MUSIC =10002 ;
   //2：英文儿歌
   public static final int ENGLISH_MUSIC =10003 ;
   //胎教歌曲
   public static final int BABYSONG_MUSIC =10004 ;
   //三字儿歌
   public static final int THREESONG_MUSIC =10005 ;
   //幼儿园
   public static final int KINDERGARTENSONG_MUSIC =10006 ;
   
   
   
   
   
   

}
