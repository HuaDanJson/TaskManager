package com.education.myoschinatest.ui.Home1;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.bean.DBTaskBean;
import com.education.myoschinatest.utils.DBTaskBeanUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckTaskActivity extends BaseActivity {

    @BindView(R.id.rlv_check_task_activity) RecyclerView mRecyclerView;

    private List<DBTaskBean> dbTaskBeanList = new ArrayList<>();

    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chack_task);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    public void initRecyclerView() {
        dbTaskBeanList = DBTaskBeanUtils.getInstance().queryAllData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(dbTaskBeanList, this);
        mRecyclerView.setAdapter(taskAdapter);
    }
}
