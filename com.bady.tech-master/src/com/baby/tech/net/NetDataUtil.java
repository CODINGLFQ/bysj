package com.baby.tech.net;

import org.json.JSONObject;

import android.content.Context;

import com.baby.tech.utils.Util;

public class NetDataUtil {

    public static String register(Context ctx, int nEvent, String mstrUser) {
        try {
            JSONObject JsonObject = new JSONObject();
            String strSiminfo = Util.getSIMCardInfo(ctx);
            JsonObject.put("qtype", "register");
            JsonObject.put("name", mstrUser);
            JsonObject.put("simid", strSiminfo);
            return JsonObject.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String registerEx(Context ctx, String mstrNickName,
            String strPwd, String strPhone) {
        try {
            JSONObject JsonObject = new JSONObject();
            String strSiminfo = Util.getSIMCardInfo(ctx);
            JsonObject.put("cmd", 3);
            JsonObject.put("name", mstrNickName);
            JsonObject.put("pwd", strPwd);
            JsonObject.put("mobile", strPhone);
            JsonObject.put("sim", strSiminfo);
            JsonObject.put("email", "");
            return JsonObject.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String login(Context ctx, int uid, String strPwd) {
        try {
            JSONObject JsonObject = new JSONObject();
            String strSiminfo = Util.getSIMCardInfo(ctx);
            JsonObject.put("cmd", 4);
            JsonObject.put("pwd", strPwd);
            JsonObject.put("uid", uid);
            JsonObject.put("sim", strSiminfo);
            return JsonObject.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
