package com.education.myoschinatest.ui.Home3;

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
import com.education.myoschinatest.ui.other.login.LoginActivity;
import com.education.myoschinatest.utils.ToastHelper;
import com.education.myoschinatest.utils.YiLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;

public class Fragment3 extends BaseFragment {

    @BindView(R.id.tv_log_out_fragment3) TextView tvLogOutFragment3;

    Unbinder unbinder;
    @BindView(R.id.tv_check_user_info_fragment3) TextView tvCheckUserInfoFragment3;
    private DBTaskManagerUserInfoBean mCurrentUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentUser = BmobUser.getCurrentUser(DBTaskManagerUserInfoBean.class);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment3;
    }

    public static Fragment3 instanceFragment() {
        Fragment3 fragment3 = new Fragment3();
        return fragment3;
    }


    @OnClick(R.id.tv_log_out_fragment3)
    public void onLogOutClicked(View view) {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @OnClick(R.id.tv_check_user_info_fragment3)
    public void onCheckUserinfoClicked(View view) {
        YiLog.D("mCurrentUser = " + mCurrentUser);
        if (mCurrentUser != null && mCurrentUser.getTypeOfWorkManager() == 0) {
            startActivity(new Intent(getActivity(), AllUserActivity.class));
        } else {
            ToastHelper.showShortMessage("只有管理员才可以查看所有用户信息");
        }
    }

    @OnClick(R.id.tv_send_message_fragment3)
    public void onSendMessageClicked(View view) {
        YiLog.D("mCurrentUser = " + mCurrentUser);
        if (mCurrentUser != null && mCurrentUser.getTypeOfWorkManager() == 0) {
            startActivity(new Intent(getActivity(), SendMessageActivity.class));
        } else {
            ToastHelper.showShortMessage("只有管理员才可以发送公告");
        }
    }
}

