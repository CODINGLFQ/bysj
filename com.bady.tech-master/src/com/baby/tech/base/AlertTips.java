package com.baby.tech.base;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.baby.tech.R;
import com.baby.tech.db.ResultCode;

public class AlertTips {

    public static void notify(Context ctx, int error) {
        String strTips = null;
        switch (error) {
        case ResultCode.DEFAULT_SUCCESS:
            strTips = ctx.getString(R.string.text_success);
            break;
        case ResultCode.DEFAULT_FAILED:
        case ResultCode.HTTP_STATUS_BAD_REQUEST:
        case ResultCode.HTTP_STATUS_NOT_FOUND:
        case ResultCode.HTTP_STATUS_DEFAULT_EXCEPTION:
            strTips = ctx.getString(R.string.text_unknown_error);
            break;
        case ResultCode.HTTP_STATUS_INTERNAL_SERVER_ERROR:
        case ResultCode.HTTP_STATUS_CONN_EXCEPTION:
            strTips = ctx.getString(R.string.text_net_server_exception);
            break;
        case ResultCode.HTTP_STATUS_SOCKET_TIMEOUT:
        case ResultCode.HTTP_STATUS_CONN_TIMEOUT:
            strTips = ctx.getString(R.string.text_net_busy);
            break;
        case ResultCode.HTTP_STATUS_INTERRUPTED_IO_EXCEPTION:
            strTips = ctx.getString(R.string.text_net_interrupted);
            break;
        case ResultCode.HTTP_STATUS_IO_EXCEPTION:
            strTips = ctx.getString(R.string.text_net_io_exception);
            break;
        case ResultCode.NETWORK_SERVICE_UNAVAILABLE:
            strTips = ctx.getString(R.string.text_network_unavailable);
            break;
        case ResultCode.NETWORK_SERVICE_AVAILABLE:
            strTips = ctx.getString(R.string.text_network_available);
            break;
        case ResultCode.FEATURE_UNSUPPORTED:
            strTips = ctx.getString(R.string.text_feature_not_supported);
            break;
        default:
            break;
        }

        if (null != strTips) {
            Toast t = Toast.makeText(ctx, strTips, Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            t.show();
        }
    }

    // public static void showCarListTips(Context ctx,
    // List<CarBindingInfo> infolist) {
    //
    // if (null == infolist) {
    // return;
    // }
    //
    // StringBuffer sb = new StringBuffer();
    // for (int i = 0; i < infolist.size(); i++) {
    // String strTips = "plateno:" + infolist.get(i).mPlate + ";"
    // + "imsi:" + infolist.get(i).mIMSI + "\n";
    // sb.append(strTips);
    // }
    // Toast t = Toast.makeText(ctx, sb.toString(), Toast.LENGTH_LONG);
    // t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    // t.show();
    //
    // }
    //
    // public static void showCarDetailTips(Context ctx, CarDetailInfo info) {
    //
    // if (null == info) {
    // return;
    // }
    // String strTips = "imsi:" + info.mIMSI + "\n" + "x:" + info.mLongitude
    // + "\n" + "y:" + info.mLatitude + "\n" + "time:"
    // + info.mPostitionTime + "\n" + "speed:" + info.mSpeed + "\n"
    // + "dir:" + info.mDirection + "\n";
    //
    // Toast t = Toast.makeText(ctx, strTips, Toast.LENGTH_LONG);
    // t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    // t.show();
    // }
    //
    // public static void showTrackTips(Context ctx, Track trk) {
    //
    // if (null == trk) {
    // return;
    // }
    //
    // StringBuffer sb = new StringBuffer();
    // String strTips = trk.mIMSI + "\n";
    // sb.append(strTips);
    // for (int i = 0; i < trk.mTrackEleArray.size(); i++) {
    // CarDetailInfo info = trk.mTrackEleArray.get(i);
    // strTips = "x:" + info.mLongitude + "\n" + "y:" + info.mLatitude
    // + "\n" + "time:" + info.mPostitionTime + "\n" + "speed:"
    // + info.mSpeed + "\n" + "dir:" + info.mDirection + "\n";
    // sb.append(strTips);
    //
    // if (i > 3) {
    // sb.append("...");
    // break;
    // }
    // }
    // Toast t = Toast.makeText(ctx, sb.toString(), Toast.LENGTH_LONG);
    // t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    // t.show();
    // }
}
