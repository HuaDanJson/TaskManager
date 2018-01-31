package com.education.myoschinatest.ui;

import android.os.Bundle;
import android.util.Log;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.ui.other.login.LoginActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bmob.initialize(this, "5f38f08929314ed5b3f0f4992b847582");
        BmobInstallationManager.getInstallationId();
        BmobInstallationManager.getInstance().getCurrentInstallation();
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.i("bmob", bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    Log.i("bmob", e.getMessage());
                }
            }
        });

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
