package com.education.myoschinatest.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.notification.NotificationBean;
import com.education.myoschinatest.ui.Home1.Fragment1;
import com.education.myoschinatest.ui.Home2.Fragment2;
import com.education.myoschinatest.ui.Home3.Fragment3;
import com.education.myoschinatest.ui.other.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

public class MainActivity extends BaseActivity {
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    public static final String HOME_CURRENT_TAB_POSITION = "HOME_CURRENT_TAB_POSITION";

    private CommonTabLayout bottomTabLayout;
    private String[] mTitles = {"任务", "消息", "我的"};
    private int[] mIconUnselectIds = {R.drawable.icon_job, R.drawable.icon_news, R.mipmap.ic_user};
    private int[] mIconSelectIds = {R.drawable.icon_job_selected, R.drawable.icon_news_selected, R.mipmap.ic_user_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabLayout = (CommonTabLayout) findViewById(R.id.main_tab_layout);
        initTab();
        initFragment(savedInstanceState);
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
    }

    //用来初始化底部导航
    private void initTab() {
        for (int i = 0; i < mIconSelectIds.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        bottomTabLayout.setTabData(mTabEntities);
        bottomTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                SwitchTo(position);

            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    //

    /**
     * 初始化碎片
     * private BianMinFragment bianMinFragment;
     * private CYFragment cyFragment;
     * private ChatFragment ChatFragment;
     * private jtFragment jtFragment;
     */
    public void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {//判断保留的数据是否为空

            fragment1 = (Fragment1) getSupportFragmentManager().findFragmentByTag("fragment1");
            fragment2 = (Fragment2) getSupportFragmentManager().findFragmentByTag("fragment2");
            fragment3 = (Fragment3) getSupportFragmentManager().findFragmentByTag("fragment3");
            currentTabPosition = savedInstanceState.getInt(HOME_CURRENT_TAB_POSITION);
        } else {
            fragment1 = Fragment1.instanceFragment();
            fragment2 = Fragment2.instanceFragment();
            fragment3 = Fragment3.instanceFragment();

            //transaction里添加fragment
            transaction.add(R.id.linearlayout_main, fragment1, "fragment1");
            transaction.add(R.id.linearlayout_main, fragment2, "fragment2");
            transaction.add(R.id.linearlayout_main, fragment3, "fragment3");


        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        bottomTabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(fragment1);
                transaction.hide(fragment2);
                transaction.hide(fragment3);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(fragment2);
                transaction.hide(fragment1);
                transaction.hide(fragment3);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.show(fragment3);
                transaction.hide(fragment2);
                transaction.hide(fragment1);
                transaction.commitAllowingStateLoss();
                break;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventData(NotificationBean notificationBean) {
//      定义一个PendingIntent,点击Intent可以启动一个新的Intent
        Log.i("bmob", "getEventData ：" + notificationBean.getAlert());
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        PendingIntent pit = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//                设置图片文字提示方式等等
        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        builder.setContentTitle("您有 任务系统 新的消息")                        //标题
                .setContentText(notificationBean.getAlert())      //内容
                .setSubText("——内容下面的一小段文字")                    //内容下面的一小段文字
                .setTicker("收到信息后状态栏显示的文字信息~")             //收到信息后状态栏显示的文字信息
                .setWhen(System.currentTimeMillis())           //设置通知时间
                .setSmallIcon(R.drawable.icon_logo)            //设置小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_logo))
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                .setAutoCancel(true)                           //设置点击后取消Notification
                .setContentIntent(pit);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }


}