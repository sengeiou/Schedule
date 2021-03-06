package com.android.mb.schedule.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.android.mb.schedule.R;
import com.android.mb.schedule.constants.ProjectConstants;
import com.android.mb.schedule.entitys.CurrentUser;
import com.android.mb.schedule.utils.Helper;
import com.android.mb.schedule.utils.NavigationHelper;
import com.android.mb.schedule.utils.PreferencesHelper;

import cn.jpush.android.api.JPushInterface;


/**
 * 引导页
 * @author cgy
 */

public class LoadingActivity extends AppCompatActivity {
    private static final int LOADING_TIME_OUT = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除信号栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getRegisterId();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                if (CurrentUser.getInstance().isLogin()){
                    if (CurrentUser.getInstance().getIs_bind_wx()==1){
                        NavigationHelper.startActivity(LoadingActivity.this, MainActivity.class, null, true);
                    }else{
                        NavigationHelper.startActivity(LoadingActivity.this, LoginActivity.class, null, true);
                    }
                }else{
                    NavigationHelper.startActivity(LoadingActivity.this, LoginActivity.class, null, true);
                }
            }

        }, LOADING_TIME_OUT);
    }

    private void getRegisterId(){
        String rid = JPushInterface.getRegistrationID(getApplicationContext());
        if (Helper.isNotEmpty(rid)) {
            PreferencesHelper.getInstance().putString(ProjectConstants.KEY_REGISTRATION_ID,rid);
            Log.d("jpush rid:",rid);
        }
    }

}
