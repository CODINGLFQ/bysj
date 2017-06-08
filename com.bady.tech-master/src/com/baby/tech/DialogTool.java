package com.baby.tech;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;

/**
 * 对话框封装类
 * 
 * @author Z
 * 
 */
public class DialogTool {

    public static final int NO_ICON = -1; // 无图�?

    /**
     * 创建消息对话�?
     * 
     * @param context
     *            上下�? 必填
     * @param iconId
     *            图标，如：R.drawable.icon �? DialogTool.NO_ICON 必填
     * @param title
     *            标题 必填
     * @param message
     *            显示内容 必填
     * @param btnName
     *            按钮名称 必填
     * @param listener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createMessageDialog(Context context, String title,
            String message, String btnName, OnClickListener listener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            // 设置对话框图�?
            builder.setIcon(iconId);
        }
        // 设置对话框标�?
        builder.setTitle(title);
        // 设置对话框消�?
        builder.setMessage(message);
        // 设置按钮
        builder.setPositiveButton(btnName, listener);
        // 创建�?个消息对话框
        dialog = builder.create();

        return dialog;
    }

    /**
     * 创建警示（确认�?�取消）对话�?
     * 
     * @param context
     *            上下�? 必填
     * @param iconId
     *            图标，如：R.drawable.icon �? DialogTool.NO_ICON 必填
     * @param title
     *            标题 必填
     * @param message
     *            显示内容 必填
     * @param positiveBtnName
     *            确定按钮名称 必填
     * @param negativeBtnName
     *            取消按钮名称 必填
     * @param positiveBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createConfirmDialog(Context context, String title,
            String message, String positiveBtnName, String negativeBtnName,
            OnClickListener positiveBtnListener,
            OnClickListener negativeBtnListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            // 设置对话框图�?
            builder.setIcon(iconId);
        }
        // 设置对话框标�?
        builder.setTitle(title);
        // 设置对话框消�?
        builder.setMessage(message);
        // 设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        // 设置取消按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        // 创建�?个消息对话框
        dialog = builder.create();

        return dialog;
    }

    /**
     * 创建单�?�对话框
     * 
     * @param context
     *            上下�? 必填
     * @param iconId
     *            图标，如：R.drawable.icon �? DialogTool.NO_ICON 必填
     * @param title
     *            标题 必填
     * @param itemsString
     *            选择�? 必填
     * @param positiveBtnName
     *            确定按钮名称 必填
     * @param negativeBtnName
     *            取消按钮名称 必填
     * @param positiveBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param itemClickListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createSingleChoiceDialog(Context context,
            String title, String[] itemsString, String positiveBtnName,
            String negativeBtnName, OnClickListener positiveBtnListener,
            OnClickListener negativeBtnListener,
            OnClickListener itemClickListener, int iconId, int selectId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            // 设置对话框图�?
            builder.setIcon(iconId);
        }
        // 设置对话框标�?
        builder.setTitle(title);
        // 设置单�?��?�项, 参数0: 默认第一个单选按钮被选中
        builder.setSingleChoiceItems(itemsString, selectId, itemClickListener);
        // 设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        // 设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        // 创建�?个消息对话框
        dialog = builder.create();

        return dialog;
    }

    /**
     * 创建复�?�对话框
     * 
     * @param context
     *            上下�? 必填
     * @param iconId
     *            图标，如：R.drawable.icon �? DialogTool.NO_ICON 必填
     * @param title
     *            标题 必填
     * @param itemsString
     *            选择�? 必填
     * @param positiveBtnName
     *            确定按钮名称 必填
     * @param negativeBtnName
     *            取消按钮名称 必填
     * @param positiveBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param itemClickListener
     *            监听器，�?实现android.content.DialogInterface.
     *            OnMultiChoiceClickListener;接口 必填
     * @return
     */
    public static Dialog createMultiChoiceDialog(Context context, String title,
            String[] itemsString, String positiveBtnName,
            String negativeBtnName, OnClickListener positiveBtnListener,
            OnClickListener negativeBtnListener,
            OnMultiChoiceClickListener itemClickListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            // 设置对话框图�?
            builder.setIcon(iconId);
        }
        // 设置对话框标�?
        builder.setTitle(title);
        // 设置选项
        builder.setMultiChoiceItems(itemsString, null, itemClickListener);
        // 设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        // 设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        // 创建�?个消息对话框
        dialog = builder.create();

        return dialog;
    }

    /**
     * 创建列表对话�?
     * 
     * @param context
     *            上下�? 必填
     * @param iconId
     *            图标，如：R.drawable.icon �? DialogTool.NO_ICON 必填
     * @param title
     *            标题 必填
     * @param itemsString
     *            列表�? 必填
     * @param negativeBtnName
     *            取消按钮名称 必填
     * @param negativeBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @return
     */
    public static Dialog createListDialog(Context context, String title,
            String[] itemsString, String negativeBtnName,
            OnClickListener negativeBtnListener,
            OnClickListener itemClickListener, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            // 设置对话框图�?
            builder.setIcon(iconId);
        }
        // 设置对话框标�?
        builder.setTitle(title);
        // 设置列表选项
        builder.setItems(itemsString, itemClickListener);
        // 设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        // 创建�?个消息对话框
        dialog = builder.create();

        return dialog;
    }

    /**
     * 创建自定义（含确认�?�取消）对话�?
     * 
     * @param context
     *            上下�? 必填
     * @param iconId
     *            图标，如：R.drawable.icon �? DialogTool.NO_ICON 必填
     * @param title
     *            标题 必填
     * @param positiveBtnName
     *            确定按钮名称 必填
     * @param negativeBtnName
     *            取消按钮名称 必填
     * @param positiveBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param negativeBtnListener
     *            监听器，�?实现android.content.DialogInterface.OnClickListener接口 必填
     * @param view
     *            对话框中自定义视�? 必填
     * @return
     */
    public static Dialog createRandomDialog(Context context, String title,
            String positiveBtnName, String negativeBtnName,
            OnClickListener positiveBtnListener,
            OnClickListener negativeBtnListener, View view, int iconId) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (iconId != NO_ICON) {
            // 设置对话框图�?
            builder.setIcon(iconId);
        }
        // 设置对话框标�?
        builder.setTitle(title);
        builder.setView(view);
        // 设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        // 设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        // 创建�?个消息对话框
        dialog = builder.create();

        return dialog;
    }

}