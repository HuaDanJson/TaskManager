package com.education.myoschinatest.ui.Home1;

import android.app.Activity;
import android.support.v7.widget.AppCompatButton;
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

public class DeleteTaskAdapter extends RecyclerView.Adapter<DeleteTaskAdapter.DeleteTaskAdapterViewHolder> {

    private List<DBTaskBean> dbTaskBeanList = new ArrayList<>();
    private Activity activity;
    private String mTask[] = {"管理员刚发起任务", "设计", "数冲", "压铆", "折弯", "焊接", "打磨", "喷塑", "装配", "出库"};
    public DeleteTaskAdapterListener fixTaskAdapterListener;

    public DeleteTaskAdapter(List<DBTaskBean> dbTaskBeanList, Activity activity) {
        this.dbTaskBeanList = dbTaskBeanList;
        this.activity = activity;
    }

    @Override
    public DeleteTaskAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_delete_task_activity, parent, false);
        DeleteTaskAdapterViewHolder holder = new DeleteTaskAdapterViewHolder(view);
        return holder;
    }

    public void setFixTaskAdapterListener(DeleteTaskAdapterListener fixTaskAdapterListener) {
        this.fixTaskAdapterListener = fixTaskAdapterListener;
    }

    @Override
    public void onBindViewHolder(DeleteTaskAdapterViewHolder holder, final int position) {
        holder.tvItemTitle.setText("任务名字：" + dbTaskBeanList.get(position).getTaskName());
        holder.tvItemNumber.setText("任务编号：" + dbTaskBeanList.get(position).getTaskNumber());

        holder.tvItemDes.setText("任务描述：" + dbTaskBeanList.get(position).getTaskDescribe());
        SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String createdTime2 = sdr1.format(new Date(dbTaskBeanList.get(position).getCreatTimeAsId()));
        holder.tvItemTime.setText("任务开始时间：" + createdTime2);
        if (dbTaskBeanList.get(position).getTaskProgress() < 9) {
            holder.tvItemProgressr.setText("任务进度：任务已完成到：" + mTask[dbTaskBeanList.get(position).getTaskProgress()] + "， 即将开始的工序：" + mTask[dbTaskBeanList.get(position).getTaskProgress() + 1]);
        } else {
            holder.tvItemProgressr.setText("任务进度:恭喜该任务已经全部完成，大家辛苦！！");
        }

        holder.btnItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fixTaskAdapterListener.onDeleteTaskProgressClick(dbTaskBeanList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbTaskBeanList.size() == 0 ? 0 : dbTaskBeanList.size();
    }

    public void setData(List<DBTaskBean> dbTaskBeanList) {
        this.dbTaskBeanList = dbTaskBeanList;
        notifyDataSetChanged();
    }

    public interface DeleteTaskAdapterListener {

        void onDeleteTaskProgressClick(DBTaskBean dbTaskBean, int position);
    }

    public class DeleteTaskAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_title_delete_task_adapter) TextView tvItemTitle;
        @BindView(R.id.tv_item_number_delete_task_adapter) TextView tvItemNumber;
        @BindView(R.id.tv_item_time_delete_task_adapter) TextView tvItemTime;
        @BindView(R.id.tv_item_progress_delete_task_adapter) TextView tvItemProgressr;
        @BindView(R.id.tv_item_des_delete_task_adapter) TextView tvItemDes;
        @BindView(R.id.tv_item_finish_delete_task_adapter) AppCompatButton btnItemDelete;
        @BindView(R.id.ll_item_all_delete_task_adapter) LinearLayout llTaskAll;

        public DeleteTaskAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
