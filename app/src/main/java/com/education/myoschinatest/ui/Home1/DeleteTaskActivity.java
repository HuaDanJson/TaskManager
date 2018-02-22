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
import com.education.myoschinatest.utils.DBTaskBeanUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import cn.bmob.v3.listener.UpdateListener;

public class DeleteTaskActivity extends BaseActivity implements DeleteTaskAdapter.DeleteTaskAdapterListener {

    @BindView(R.id.rlv_delete_task_activity) RecyclerView mRecyclerView;
    @BindView(R.id.tv_delete_tips) TextView tvDeleteTips;
    private DeleteTaskAdapter mDeleteTaskAdapter;
    private List<DBTaskBean> dbTaskBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_task);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    public void initRecyclerView() {
        dbTaskBeanList = DBTaskBeanUtils.getInstance().queryAllData();
        if (dbTaskBeanList.size() == 0) {
            tvDeleteTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            tvDeleteTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mDeleteTaskAdapter = new DeleteTaskAdapter(dbTaskBeanList, this);
        mDeleteTaskAdapter.setFixTaskAdapterListener(this);
        mRecyclerView.setAdapter(mDeleteTaskAdapter);
    }

    @Override
    public void onDeleteTaskProgressClick(DBTaskBean dbDeleteTaskBean, int position) {

        for (DBTaskBean dbTaskBean : dbTaskBeanList) {
            if (dbTaskBean.getCreatTimeAsId() == dbDeleteTaskBean.getCreatTimeAsId()) {
                DBTaskBeanUtils.getInstance().deleteOneData(dbTaskBean);
                dbTaskBean.delete(dbTaskBean.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "删除当前任务成功");
                        } else {
                            Log.i("bmob", "删除当前任务失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
                dbTaskBeanList.remove(dbTaskBean);
                break;
            }
        }

        mDeleteTaskAdapter.setData(dbTaskBeanList);
        if (dbTaskBeanList.size() == 0) {
            tvDeleteTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            tvDeleteTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        }

        BmobPushManager bmobPushManager = new BmobPushManager();
        bmobPushManager.pushMessageAll("管理员删除了：" + dbDeleteTaskBean.getTaskNumber() + "号任务", new PushListener() {
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
