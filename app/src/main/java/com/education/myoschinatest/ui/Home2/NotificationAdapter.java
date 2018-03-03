package com.education.myoschinatest.ui.Home2;

import android.app.Activity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.db.DBNotificationBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jason on 2018/2/3.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationAdapterViewHolder> {

    private List<DBNotificationBean> dbTaskBeanList = new ArrayList<>();
    private Activity activity;
    public NotificationAdapterListener notificationAdapterListener;

    public NotificationAdapter(List<DBNotificationBean> dbTaskBeanList, Activity activity) {
        this.dbTaskBeanList = dbTaskBeanList;
        this.activity = activity;
    }

    @Override
    public NotificationAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_notification_fragment, parent, false);
        NotificationAdapterViewHolder holder = new NotificationAdapterViewHolder(view);
        return holder;
    }

    public void setNotificationAdapterListener(NotificationAdapterListener fixTaskAdapterListener) {
        this.notificationAdapterListener = fixTaskAdapterListener;
    }

    @Override
    public void onBindViewHolder(NotificationAdapterViewHolder holder, final int position) {

        SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String createdTime2 = sdr1.format(new Date(dbTaskBeanList.get(position).getCreatTimeAsId()));
        holder.mTime.setText("消息通知时间：" + createdTime2);
        holder.mValue.setText("消息通知内容：" + dbTaskBeanList.get(position).getAlert());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationAdapterListener.onDeleteNotificationClick(dbTaskBeanList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbTaskBeanList.size() == 0 ? 0 : dbTaskBeanList.size();
    }

    public void setData(List<DBNotificationBean> dbTaskBeanList) {
        this.dbTaskBeanList = dbTaskBeanList;
        notifyDataSetChanged();
    }

    public interface NotificationAdapterListener {

        void onDeleteNotificationClick(DBNotificationBean dbNotificationBean, int position);
    }

    public class NotificationAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_notification_time) TextView mTime;
        @BindView(R.id.tv_item_notification_delete) AppCompatButton mDelete;
        @BindView(R.id.tv_item_notification_value) TextView mValue;

        public NotificationAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
