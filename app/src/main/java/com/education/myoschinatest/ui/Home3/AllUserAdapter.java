package com.education.myoschinatest.ui.Home3;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBTaskManagerUserInfoBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jason on 2018/2/3.
 */

public class AllUserAdapter extends RecyclerView.Adapter<AllUserAdapter.AllUserAdapterViewHolder> {

    private List<DBTaskManagerUserInfoBean> mDBTaskManagerUserInfoBeanList = new ArrayList<>();
    private Activity activity;

    public AllUserAdapter(List<DBTaskManagerUserInfoBean> dbTaskManagerUserInfoBeanList, Activity activity) {
        this.mDBTaskManagerUserInfoBeanList = dbTaskManagerUserInfoBeanList;
        this.activity = activity;
    }

    @Override
    public AllUserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_all_user_adapter, parent, false);
        AllUserAdapterViewHolder holder = new AllUserAdapterViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(AllUserAdapterViewHolder holder, final int position) {

        holder.tvUserName.setText("用户名字：" + mDBTaskManagerUserInfoBeanList.get(position).getName());
        holder.tvUserAge.setText("用户年龄：" + mDBTaskManagerUserInfoBeanList.get(position).getOld());
        holder.tvUserTellPhone.setText("用户手机号：" + mDBTaskManagerUserInfoBeanList.get(position).getTellPhone());
        holder.tvUserMail.setText("用户邮箱：" + mDBTaskManagerUserInfoBeanList.get(position).getMail());
        holder.tvUserTypeOfWork.setText("用户职位：" + mDBTaskManagerUserInfoBeanList.get(position).getTypeOfWork());
        SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String createdTime2 = sdr1.format(new Date(mDBTaskManagerUserInfoBeanList.get(position).getCreatTimeAsId()));
        holder.tvUserCreateTimeItem.setText("用户注册时间：" + createdTime2);
    }

    @Override
    public int getItemCount() {
        return mDBTaskManagerUserInfoBeanList.size() == 0 ? 0 : mDBTaskManagerUserInfoBeanList.size();
    }

    public void setData(List<DBTaskManagerUserInfoBean> dbTaskBeanList) {
        this.mDBTaskManagerUserInfoBeanList = dbTaskBeanList;
        notifyDataSetChanged();
    }

    public class AllUserAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_name_item_all_user_adapter) TextView tvUserName;
        @BindView(R.id.tv_user_age_item_all_user_adapter) TextView tvUserAge;
        @BindView(R.id.tv_user_tell_phone_item_all_user_adapter) TextView tvUserTellPhone;
        @BindView(R.id.tv_user_mail_item_all_user_adapter) TextView tvUserMail;
        @BindView(R.id.tv_user_type_of_work_item_all_user_adapter) TextView tvUserTypeOfWork;
        @BindView(R.id.tv_user_create_time_item_all_user_adapter) TextView tvUserCreateTimeItem;

        public AllUserAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
