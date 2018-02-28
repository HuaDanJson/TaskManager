package com.education.myoschinatest.ui.Home3;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.bean.DBTaskManagerUserInfoBean;
import com.education.myoschinatest.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class AllUserActivity extends BaseActivity {

    @BindView(R.id.rlv_all_user_activity) RecyclerView mRecyclerView;
    @BindView(R.id.tv_tips_all_user_activity) TextView mTips;
    private List<DBTaskManagerUserInfoBean> dbTaskManagerUserInfoBeanList = new ArrayList<>();
    private AllUserAdapter mAllUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user2);
        ButterKnife.bind(this);
        getAllUser();
    }

    public void getAllUser() {
        BmobQuery<DBTaskManagerUserInfoBean> query = new BmobQuery<DBTaskManagerUserInfoBean>();
        query.order("-createdAt");
        query.setLimit(20);
        query.findObjects(new FindListener<DBTaskManagerUserInfoBean>() {
            @Override
            public void done(List<DBTaskManagerUserInfoBean> object, BmobException e) {
                if (e == null) {
                    Log.i("aaa", "checkAllUserSuccess " + object.toString());
                    dbTaskManagerUserInfoBeanList = object;
                    initRecyclerView();
                } else {
                    Log.i("aaa", "checkAllUserFailed ");
                    ToastHelper.showShortMessage("用户信息查询失败 Reason：" + e);
                    mTips.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    public void initRecyclerView() {
        if (dbTaskManagerUserInfoBeanList.size() == 0) {
            mTips.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTips.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAllUserAdapter = new AllUserAdapter(dbTaskManagerUserInfoBeanList, this);
        mRecyclerView.setAdapter(mAllUserAdapter);
    }

}
