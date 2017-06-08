package com.baby.tech.activity.base;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.baby.tech.base.MyApplication;

/**
 * activity基类
 * 
 * @author fw
 * 
 */
public class BaseActivity extends Activity {
    // common data
    protected Context mContext = null;
    // 状态提示
    // application
    protected MyApplication mCommonApplication;
    // 开始时间（统计软件使用时长时使用）
    public String mbeginTime = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getWindow().setFormat(PixelFormat.RGBA_8888);
        mCommonApplication = MyApplication.instance();
        if (mCommonApplication == null) {
            mCommonApplication = (MyApplication) this.getApplication();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
     * @OnClick(R.id.iv_head_return_left)void getBack() { finish(); }
     */
    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /** 返回 **/
    public void goBack(View v) {
        finish();
    }

    /**
     * 功能：根据资源ID获得名称
     * 
     * @param res_string_id
     *            资源ID
     * @return 名称
     */
    public String getResString(int res_string_id) {
        return mContext.getResources().getString(res_string_id);
    }

    /**
     * 功能：获取数据从Intent当中, 存储格式(key, value)
     * 
     * @param name
     *            is key
     * @return value
     */
    public Serializable getDataFromIntent(String name) {
        Intent it = getIntent();
        if (it != null) {
            return getIntent().getSerializableExtra(name);
        } else {
            return null;
        }

    }

    public void showToast(String showString) {
        Toast.makeText(getApplicationContext(), showString, Toast.LENGTH_SHORT)
                .show();
    }

    public void showToast(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT)
                .show();
    }

    public void recieveAction() {
    }

}
