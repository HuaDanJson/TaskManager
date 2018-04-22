package com.education.myoschinatest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2018/1/27.
 */

@Entity
public class DBTaskBean extends BmobObject {
    @Id(autoincrement = false)
    public long creatTimeAsId;//录入的具体数据的时间作为ID
    @Property(nameInDb = "DBTaskBean")
    public String taskName;
    public String taskDescribe;
    public String TaskNumber;
    public String taskProgressDescribe;
    public int taskProgress;
    public String others;//作为任务补充
    public long endTime;//任务结束时间

    @Generated(hash = 1254777222)
    public DBTaskBean(long creatTimeAsId, String taskName, String taskDescribe,
            String TaskNumber, String taskProgressDescribe, int taskProgress, String others,
            long endTime) {
        this.creatTimeAsId = creatTimeAsId;
        this.taskName = taskName;
        this.taskDescribe = taskDescribe;
        this.TaskNumber = TaskNumber;
        this.taskProgressDescribe = taskProgressDescribe;
        this.taskProgress = taskProgress;
        this.others = others;
        this.endTime = endTime;
    }

    @Generated(hash = 998821618)
    public DBTaskBean() {
    }

    public long getCreatTimeAsId() {
        return this.creatTimeAsId;
    }

    public void setCreatTimeAsId(long creatTimeAsId) {
        this.creatTimeAsId = creatTimeAsId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescribe() {
        return this.taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public String getTaskNumber() {
        return this.TaskNumber;
    }

    public void setTaskNumber(String TaskNumber) {
        this.TaskNumber = TaskNumber;
    }

    public String getTaskProgressDescribe() {
        return this.taskProgressDescribe;
    }

    public void setTaskProgressDescribe(String taskProgressDescribe) {
        this.taskProgressDescribe = taskProgressDescribe;
    }

    public int getTaskProgress() {
        return this.taskProgress;
    }

    public void setTaskProgress(int taskProgress) {
        this.taskProgress = taskProgress;
    }

    public String getOthers() {
        return this.others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "DBTaskBean{" +
                "creatTimeAsId=" + creatTimeAsId +
                ", taskName='" + taskName + '\'' +
                ", taskDescribe='" + taskDescribe + '\'' +
                ", TaskNumber='" + TaskNumber + '\'' +
                ", taskProgressDescribe='" + taskProgressDescribe + '\'' +
                ", taskProgress=" + taskProgress +
                ", others='" + others + '\'' +
                '}';
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
