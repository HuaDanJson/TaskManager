package com.education.myoschinatest.ui.Home1;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.education.myoschinatest.R;
import com.education.myoschinatest.bean.DBTaskBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jason on 2018/2/3.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder> {

    private List<DBTaskBean> dbTaskBeanList = new ArrayList<>();
    private Activity activity;
    private String mTask[] = {"管理员刚发起任务", "设计", "数冲", "压铆", "折弯", "焊接", "打磨", "喷塑", "装配", "出库"};

    public TaskAdapter(List<DBTaskBean> dbTaskBeanList, Activity activity) {
        this.dbTaskBeanList = dbTaskBeanList;
        this.activity = activity;
    }

    @Override
    public TaskAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_check_task_activity, parent, false);
        TaskAdapterViewHolder holder = new TaskAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskAdapterViewHolder holder, int position) {
        holder.tvItemTitle.setText("任务名字：" + dbTaskBeanList.get(position).getTaskName());
        holder.tvItemNumber.setText("任务编号：" + dbTaskBeanList.get(position).getTaskNumber());

        holder.tvItemDes.setText("任务描述：" + dbTaskBeanList.get(position).getTaskDescribe());
        SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String createdTime2 = sdr1.format(new Date(dbTaskBeanList.get(position).getCreatTimeAsId()));
        holder.tvItemTime.setText("任务开始时间：" + createdTime2);
        if (dbTaskBeanList.get(position).getTaskProgress() < 9) {
            holder.mEndTime.setVisibility(View.GONE);
            holder.tvItemProgressr.setText("任务进度：任务已完成到：" + mTask[dbTaskBeanList.get(position).getTaskProgress()] + "， 即将开始的工序：" + mTask[dbTaskBeanList.get(position).getTaskProgress() + 1]);
        } else {
            holder.mEndTime.setVisibility(View.VISIBLE);
            holder.tvItemProgressr.setText("任务进度:恭喜该任务已经全部完成，大家辛苦！！");
            SimpleDateFormat sdr3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            String createdTime3 = sdr3.format(new Date(dbTaskBeanList.get(position).getCreatTimeAsId()));
            holder.mEndTime.setText("任务结束时间：" + createdTime3);
        }

    }

    @Override
    public int getItemCount() {
        return dbTaskBeanList.size() == 0 ? 0 : dbTaskBeanList.size();
    }

    public void setData(List<DBTaskBean> dbTaskBeanList) {
        this.dbTaskBeanList = dbTaskBeanList;
        notifyDataSetChanged();
    }

    public class TaskAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_title_task_adapter) TextView tvItemTitle;
        @BindView(R.id.tv_item_number_task_adapter) TextView tvItemNumber;
        @BindView(R.id.tv_item_time_task_adapter) TextView tvItemTime;
        @BindView(R.id.tv_item_progress_task_adapter) TextView tvItemProgressr;
        @BindView(R.id.tv_item_des_task_adapter) TextView tvItemDes;
        @BindView(R.id.tv_item_end_time_task_adapter) TextView mEndTime;
        @BindView(R.id.ll_task_all_task_adapter) LinearLayout llTaskAll;

        public TaskAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
