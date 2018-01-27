package com.education.myoschinatest.ui;

import android.os.Bundle;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.ui.other.login.LoginActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bmob.initialize(this, "5f38f08929314ed5b3f0f4992b847582");
        doInUI(new Runnable() {
            @Override
            public void run() {
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser != null) {
                    // 允许用户使用应用
                    toActivity(MainActivity.class);
                    WelcomeActivity.this.finish();
                } else {
                    toActivity(LoginActivity.class);
                    WelcomeActivity.this.finish();
                }
            }
        }, 50);

    }
}
