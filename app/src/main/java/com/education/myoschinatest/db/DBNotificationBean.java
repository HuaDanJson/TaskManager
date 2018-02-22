package com.education.myoschinatest.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;

/**
 * Created by jason on 2018/2/22.
 */

@Entity
public class DBNotificationBean extends BmobObject {
    @Id(autoincrement = false)
    public long creatTimeAsId;//录入的具体数据的时间作为ID
    @Property(nameInDb = "DBNotificationBean")
    private String alert;

    @Generated(hash = 2105110825)
    public DBNotificationBean(long creatTimeAsId, String alert) {
        this.creatTimeAsId = creatTimeAsId;
        this.alert = alert;
    }

    @Generated(hash = 2066781912)
    public DBNotificationBean() {
    }

    public long getCreatTimeAsId() {
        return this.creatTimeAsId;
    }

    public void setCreatTimeAsId(long creatTimeAsId) {
        this.creatTimeAsId = creatTimeAsId;
    }

    public String getAlert() {
        return this.alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "DBNotificationBean{" +
                "creatTimeAsId=" + creatTimeAsId +
                ", alert='" + alert + '\'' +
                '}';
    }
}