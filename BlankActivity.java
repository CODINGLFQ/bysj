package com.baby.tech.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.baby.tech.R;
import com.baby.tech.activity.base.BaseActivity;

public class BlankActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_blank);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
        case R.id.action_settings:
            Log.e("test", "action_settings");
            break;
        case R.id.action_recommend:
            Log.e("test", "action_recommend");
            break;
        case R.id.action_games:
            Log.e("test", "action_games");
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}
