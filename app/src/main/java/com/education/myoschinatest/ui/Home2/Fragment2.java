package com.education.myoschinatest.ui.Home2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;
import com.education.myoschinatest.db.DBNotificationBean;
import com.education.myoschinatest.utils.DBNotificationBeanUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment2 extends BaseFragment implements NotificationAdapter.NotificationAdapterListener {

    @BindView(R.id.rlv_notification_fragment) RecyclerView mRecyclerView;
    @BindView(R.id.tv_tips_notification_fragment) TextView mTips;
    private List<DBNotificationBean> dbNotificationBeanList = new ArrayList<>();
    private NotificationAdapter notificationAdapter;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment2;
    }

    public static Fragment2 instanceFragment() {
        Fragment2 fragment2 = new Fragment2();
        return fragment2;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void initRecyclerView() {
        dbNotificationBeanList = DBNotificationBeanUtils.getInstance().queryAllData();
        if (dbNotificationBeanList.size() == 0) {
            mTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        notificationAdapter = new NotificationAdapter(dbNotificationBeanList, getActivity());
        notificationAdapter.setNotificationAdapterListener(this);
        mRecyclerView.setAdapter(notificationAdapter);
    }

    @Override
    public void onDeleteNotificationClick(DBNotificationBean dbNotificationBean, int position) {

        for (DBNotificationBean dbTaskBean : dbNotificationBeanList) {
            if (dbTaskBean.getCreatTimeAsId() == dbNotificationBean.getCreatTimeAsId()) {
                DBNotificationBeanUtils.getInstance().deleteOneData(dbTaskBean);
                dbNotificationBeanList.remove(dbTaskBean);
                break;
            }
        }
        notificationAdapter.setData(dbNotificationBeanList);
        if (dbNotificationBeanList.size() == 0) {
            mTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        }
    }
}
