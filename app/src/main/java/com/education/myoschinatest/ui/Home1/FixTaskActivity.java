package com.education.myoschinatest.ui.Home1;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.bean.DBTaskBean;
import com.education.myoschinatest.bean.DBTaskManagerUserInfoBean;
import com.education.myoschinatest.utils.DBTaskBeanUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.UpdateListener;

public class FixTaskActivity extends BaseActivity implements FixTaskAdapter.FixTaskAdapterListener {

    @BindView(R.id.rlv_fix_task_activity) RecyclerView mRecyclerView;
    @BindView(R.id.tv_update_tips) TextView mUpdateTips;
    private List<DBTaskBean> mFixDBTaskBeanList = new ArrayList<>();
    private List<DBTaskBean> mAllDBTaskBeanList = new ArrayList<>();
    private FixTaskAdapter mFixTaskAdapter;
    private DBTaskManagerUserInfoBean mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_task);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    public void initRecyclerView() {
        mCurrentUser = BmobUser.getCurrentUser(DBTaskManagerUserInfoBean.class);
        if (mCurrentUser == null) {
            return;
        }
        mAllDBTaskBeanList = DBTaskBeanUtils.getInstance().queryAllData();
        for (DBTaskBean dbTaskBean : mAllDBTaskBeanList) {
            if ((dbTaskBean.getTaskProgress() + 1) == (mCurrentUser.getTypeOfWorkManager())) {
                mFixDBTaskBeanList.add(dbTaskBean);
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mFixTaskAdapter = new FixTaskAdapter(mFixDBTaskBeanList, this);
        mFixTaskAdapter.setFixTaskAdapterListener(this);
        mRecyclerView.setAdapter(mFixTaskAdapter);
        if (mFixDBTaskBeanList.size() == 0) {
            mUpdateTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mUpdateTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onUpdateTaskProgressClick(long createTimeAsId, int position) {
        for (DBTaskBean dbTaskBean : mFixDBTaskBeanList) {
            if (dbTaskBean.getCreatTimeAsId() == createTimeAsId) {
                dbTaskBean.setTaskProgress(dbTaskBean.getTaskProgress() + 1);
                DBTaskBeanUtils.getInstance().updateData(dbTaskBean);
                dbTaskBean.update(dbTaskBean.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "更新成功");
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
                mFixDBTaskBeanList.remove(dbTaskBean);
                break;
            }
        }

        if (mFixDBTaskBeanList.size() == 0) {
            mUpdateTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mUpdateTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mFixTaskAdapter.setData(mFixDBTaskBeanList);
        }

        BmobPushManager bmobPushManager = new BmobPushManager();
        bmobPushManager.pushMessageAll(mCurrentUser.getTypeOfWork() + "更新了任务进度", new PushListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "Client 推送成功！");
                } else {
                    Log.i("bmob", "Client 异常：" + e.getMessage());
                }
            }
        });
    }
}
