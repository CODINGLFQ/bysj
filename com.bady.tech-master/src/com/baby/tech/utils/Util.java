package com.baby.tech.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.baby.tech.db.Constant;
import com.baby.tech.entity.SIMCardInfo;

/**
 * 公共的方法类
 * 
 * @author panyoufu
 */
public class Util {

    /** Log标签 */
    private static final String TAG = "Util";

    /** 输出Log的常量 */
    public static final String VALUEEQUALS = "value = ";

    /** 输出Log的常量 */
    public static final String KEYEQUALS = "key = ";

    public static final String GROUP_MSG = "Broadcast_GROUP_MSG";

    public static final String FRIEND_MSG = "Broadcast_FRIEND_MSG";

    public static final String OPEN_NET_SERVER = "Broadcast_OpenService";

    /**
     * 获取Preference中String型变量的值
     * 
     * @param ctx
     *            当前的Context
     * @param key
     *            字段名
     * 
     * @return String型变量的值
     */
    public static String getPrefString(Context ctx, String key) {

        SharedPreferences pref = ctx.getSharedPreferences(
                Constant.HLBREFERENCE, 0);
        String value = pref.getString(key, Constant.DEFAULT_STRING);
        return value;
    }

    /**
     * 设置Preference中String型变量的值
     * 
     * @param ctx
     *            当前的Context
     * @param key
     *            字段名
     * @param value
     *            字段值
     * 
     * @return 设置成功与否
     */
    @SuppressLint("NewApi")
    public static void setPrefString(Context ctx, String key, String value) {

        SharedPreferences.Editor editor = ctx.getSharedPreferences(
                Constant.HLBREFERENCE, 0).edit();
        editor.putString(key, value).apply();
    }

    /**
     * 判断String型变量是否合法
     * 
     * @param action
     *            String型变量
     * 
     * @return String型变量是否合法
     */
    public static boolean isValidString(String action) {
        return ((Constant.DEFAULT_STRING != action) && (action.trim().length() > 0));
    }

    /**
     * 获取Preference中int型变量的值
     * 
     * @param ctx
     *            当前的Context
     * @param key
     *            字段名
     * 
     * @return int型变量的值
     */
    public static int getPrefInt(Context ctx, String key) {

        SharedPreferences pref = ctx.getSharedPreferences(
                Constant.HLBREFERENCE, 0);
        int value = pref.getInt(key, Constant.DEFAULT_INT);

        return value;
    }

    /**
     * 设置Preference中int型变量的值
     * 
     * @param ctx
     *            当前的Context
     * @param key
     *            字段名
     * @param value
     *            字段值
     * 
     * @return 设置成功与否
     */
    public static boolean setPrefInt(Context ctx, String key, int value) {

        SharedPreferences.Editor editor = ctx.getSharedPreferences(
                Constant.HLBREFERENCE, 0).edit();

        return editor.putInt(key, value).commit();
    }

    public static double getPrefFloat(Context ctx, String key) {

        SharedPreferences pref = ctx.getSharedPreferences(
                Constant.HLBREFERENCE, 0);
        float value = pref.getFloat(key, Constant.DEFAULT_DOUBLE);

        return value;
    }

    public static boolean setPrefFloat(Context ctx, String key, double value) {

        SharedPreferences.Editor editor = ctx.getSharedPreferences(
                Constant.HLBREFERENCE, 0).edit();

        return editor.putFloat(key, (float) value).commit();
    }

    // GZIP解压缩
    public static String unCompressByGzip(byte[] buf) throws IOException {

        // ByteArrayInputStream in = new ByteArrayInputStream(str
        // .getBytes("ISO-8859-1"));
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[1024];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        gunzip.close();
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")

        String output = out.toString("UTF-8");
        out.flush();
        out.close();
        in.close();
        return output;
    }

    public static final int BUFFER = 1024;

    /**
     * 数据压缩
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static String CompressByZip(byte[] data) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 压缩
        // compress(bais, baos);

        GZIPOutputStream gos = new GZIPOutputStream(out);

        int count;
        byte databuf[] = new byte[BUFFER];
        while ((count = in.read(databuf, 0, BUFFER)) != -1) {
            gos.write(databuf, 0, count);
        }

        gos.finish();

        gos.flush();
        gos.close();

        String output = out.toString("UTF-8");

        out.flush();
        out.close();

        in.close();

        return output;
    }

    public static String getSIMCardInfo(Context ctx) {
        SIMCardInfo siminfo = new SIMCardInfo(ctx);
        String strSiminfo = "";
        if (siminfo != null) {
            strSiminfo = siminfo.getNativePhoneNumber();
        }
        return strSiminfo;
    }

    public static int compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                // System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                // System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compareCurDate(String strDate) {
        int nMinutes = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        try {
            Date destDate = df.parse(strDate);
            Date curDate = new Date(System.currentTimeMillis());
            nMinutes = (int) ((curDate.getTime() - destDate.getTime()) / (60 * 1000));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nMinutes;
    }

    public static String getShowAddr(String strAddr) {
        String strRet = strAddr;
        if (strAddr.length() > 14) {
            strRet = strAddr.subSequence(0, 13) + "•••";
        }
        return strRet;

    }

    public static String getShowTime(String strDate) {
        String strTime = "";
        int nDays = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date destDate = df.parse(strDate);
            Date curDate = new Date(System.currentTimeMillis());
            nDays = (int) ((curDate.getTime() - destDate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (nDays) {
        case 0:
            strTime = strDate.substring(10);
            break;
        case 1:
            strTime = "昨天" + strDate.substring(11, 16);
            break;
        case 2:
            strTime = "前天" + strDate.substring(11, 16);
            break;
        default:
            strTime = strDate.substring(0, 10);
            break;
        }
        return strTime;

    }

    public static void getMyPos(Context ctx, double mMyLat, double mMyLon) {
        mMyLat = getPrefFloat(ctx, Constant.PREF_MYPOS_LAT);
        mMyLon = getPrefFloat(ctx, Constant.PREF_MYPOS_LON);
        if (mMyLat == 0.0 || mMyLon == 0.0) {
            mMyLat = 39.933859;
            mMyLon = 116.400191;
        }
    }

    public static void saveMyPos(Context ctx, double mMyLat, double mMyLon) {
        setPrefFloat(ctx, Constant.PREF_MYPOS_LAT, mMyLat);
        setPrefFloat(ctx, Constant.PREF_MYPOS_LON, mMyLon);

    }

}
