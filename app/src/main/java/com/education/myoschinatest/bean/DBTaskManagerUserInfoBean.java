package com.education.myoschinatest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobUser;

/**
 * Created by jason on 2018/1/19.
 */
@Entity
public class DBTaskManagerUserInfoBean extends BmobUser {

    @Id(autoincrement = false)
    public long creatTimeAsId;//录入的具体数据的时间作为ID
    @Property(nameInDb = "DBTaskManagerUserInfoBean")
    public String name;
    public String old;
    public String tellPhone;
    public String mail;
    public String typeOfWork;
    public int typeOfWorkManager;

    @Generated(hash = 1690811285)
    public DBTaskManagerUserInfoBean(long creatTimeAsId, String name, String old,
                                     String tellPhone, String mail, String typeOfWork, int typeOfWorkManager) {
        this.creatTimeAsId = creatTimeAsId;
        this.name = name;
        this.old = old;
        this.tellPhone = tellPhone;
        this.mail = mail;
        this.typeOfWork = typeOfWork;
        this.typeOfWorkManager = typeOfWorkManager;
    }

    @Generated(hash = 173426095)
    public DBTaskManagerUserInfoBean() {
    }

    public long getCreatTimeAsId() {
        return this.creatTimeAsId;
    }

    public void setCreatTimeAsId(long creatTimeAsId) {
        this.creatTimeAsId = creatTimeAsId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld() {
        return this.old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getTellPhone() {
        return this.tellPhone;
    }

    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTypeOfWork() {
        return this.typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public int getTypeOfWorkManager() {
        return this.typeOfWorkManager;
    }

    public void setTypeOfWorkManager(int typeOfWorkManager) {
        this.typeOfWorkManager = typeOfWorkManager;
    }

    @Override
    public String toString() {
        return "DBTaskManagerUserInfoBean{" +
                "creatTimeAsId=" + creatTimeAsId +
                ", name='" + name + '\'' +
                ", old='" + old + '\'' +
                ", tellPhone='" + tellPhone + '\'' +
                ", mail='" + mail + '\'' +
                ", typeOfWork='" + typeOfWork + '\'' +
                ", typeOfWorkManager=" + typeOfWorkManager +
                '}';
    }
}
