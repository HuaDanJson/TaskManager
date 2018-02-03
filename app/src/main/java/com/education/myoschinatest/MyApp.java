package com.education.myoschinatest;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.education.myoschinatest.DBBeanUtils.ConstKey;
import com.education.myoschinatest.DBBeanUtils.DBBuyTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBShouChangTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBTaskManagerUserInfoBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBTicketBeanUtils;
import com.education.myoschinatest.DBBeanUtils.DBUserInfoBeanUtils;
import com.education.myoschinatest.utils.ToastHelper;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

public class MyApp extends Application {
    private static LiteOrm liteOrm;
    private static MyApp app;

    private ArrayList<Activity> activitys = new ArrayList<>();

    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initLiteOrm();
        DBUserInfoBeanUtils.Init(getApplicationContext());
        DBTicketBeanUtils.Init(getApplicationContext());
        DBBuyTicketBeanUtils.Init(getApplicationContext());
        DBShouChangTicketBeanUtils.Init(getApplicationContext());

        //用SharedPreferences 存储用户名和密码
        SharedPreferences pref = getApplicationContext().getSharedPreferences(ConstKey.KEY_WIFI_REMIND_Shared_Preference, MODE_PRIVATE);
        editor = pref.edit();
        ToastHelper.init(this);

        DBTaskManagerUserInfoBeanUtils.Init(getApplicationContext());

        //TODO 集成：1.4、初始化数据服务SDK、初始化设备信息并启动推送服务
        // 初始化BmobSDK
        Bmob.initialize(this, "5f38f08929314ed5b3f0f4992b847582");
        // 使用推送服务时的初始化操作
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
        // 启动推送服务
        BmobPush.startWork(this);
    }

    private void initLiteOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        liteOrm.setDebugged(true); // open the log
    }


    public void addActivity(Activity activity) {
        if (!activitys.contains(activity)) {
            activitys.add(activity);
        }
    }

    public void removeActiivty(Activity activity) {
        if (activitys.contains(activity)) {
            activitys.remove(activity);
        }
    }

    public void exit() {
        for (Activity a : activitys) {
            a.finish();
        }
    }

    public static MyApp getInstance() {
        return app;
    }

    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }
}
