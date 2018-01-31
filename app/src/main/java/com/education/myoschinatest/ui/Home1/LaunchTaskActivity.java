package com.education.myoschinatest.ui.Home1;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.bean.DBTaskBean;
import com.education.myoschinatest.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.SaveListener;

public class LaunchTaskActivity extends BaseActivity {

    @BindView(R.id.edt_name_launch_task_activity) EditText mNameEditText;
    @BindView(R.id.edt_number_launch_task_activity) EditText mNumberEditText;
    @BindView(R.id.edt_describe_launch_task_activity) EditText mDescribeEditText;
    @BindView(R.id.ll_commit_launch_task_activity) LinearLayout mCommitLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_task);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_commit_launch_task_activity})
    public void onCommitClicked(View view) {
        mCommitLinearLayout.setClickable(false);
        //打开抽屉
        String taskName = mNameEditText.getText().toString();
        String taskNumber = mNumberEditText.getText().toString();
        String taskDescribe = mDescribeEditText.getText().toString();

        //将用户填写的反馈意见和用户邮箱上传到服务器
        if (TextUtils.isEmpty(taskName) || TextUtils.isEmpty(taskNumber) || TextUtils.isEmpty(taskDescribe)) {
            mCommitLinearLayout.setClickable(true);
            ToastHelper.showShortMessage("请填写完订单所有数据后再提交新的订单");
        } else {
            DBTaskBean dbTaskBean = new DBTaskBean();
            dbTaskBean.setCreatTimeAsId(System.currentTimeMillis());
            dbTaskBean.setTaskName(taskName);
            dbTaskBean.setTaskNumber(taskNumber);
            dbTaskBean.setTaskDescribe(taskDescribe);
            dbTaskBean.setTaskProgressDescribe("");
            dbTaskBean.setTaskProgress(0);
            dbTaskBean.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                        mCommitLinearLayout.setClickable(true);
                        ToastHelper.showLongMessage("新任务发起成功");

                        BmobPushManager bmobPushManager = new BmobPushManager();
                        bmobPushManager.pushMessageAll("你有新的任务请注意查收", new PushListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Log.i("bmob", "Client 推送成功！");
                                } else {
                                    Log.i("bmob", "Client 异常：" + e.getMessage());
                                }
                            }
                        });
                        finish();
                    } else {
                        mCommitLinearLayout.setClickable(true);
                        ToastHelper.showLongMessage("新任务发起失败");
                    }
                }
            });
        }
    }

    @OnClick({R.id.iv_back_launch_task_activity})
    public void onBackClicked(View view) {
        finish();
    }
}
