package com.education.myoschinatest.ui.Home1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseFragment;
import com.education.myoschinatest.bean.DBTaskManagerUserInfoBean;
import com.education.myoschinatest.utils.ToastHelper;
import com.education.myoschinatest.utils.YiLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;

public class Fragment1 extends BaseFragment {

    @BindView(R.id.tv_launch_task_fragment1) TextView tvLaunchTaskFragment1;
    @BindView(R.id.tv_check_task_fragment1) TextView tvCheckTaskFragment1;
    @BindView(R.id.tv_fix_task_fragment1) TextView tvFixTaskFragment1;
    @BindView(R.id.tv_end_task_fragment1) TextView tvEndTaskFragment1;
    Unbinder unbinder;
    private DBTaskManagerUserInfoBean mCurrentUser;

    public static Fragment1 instanceFragment() {
        Fragment1 fragment1 = new Fragment1();
        return fragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mCurrentUser = BmobUser.getCurrentUser(DBTaskManagerUserInfoBean.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_launch_task_fragment1)
    public void onLaunchTaskClicked(View view) {
        YiLog.D("mCurrentUser = " + mCurrentUser);
        if (mCurrentUser != null && mCurrentUser.getTypeOfWorkManager() == 0) {
            startActivity(new Intent(getActivity(), LaunchTaskActivity.class));
        } else {
            ToastHelper.showShortMessage("只有管理员才可以添加新任务");
        }
    }

    @OnClick(R.id.tv_check_task_fragment1)
    public void onCheckTaskClicked(View view) {
        startActivity(new Intent(getActivity(), CheckTaskActivity.class));
    }

    @OnClick(R.id.tv_fix_task_fragment1)
    public void onFixTaskClicked(View view) {
        startActivity(new Intent(getActivity(), FixTaskActivity.class));
    }

    @OnClick(R.id.tv_end_task_fragment1)
    public void onEndTaskClicked(View view) {
        startActivity(new Intent(getActivity(), DeleteTaskActivity.class));
    }

}
