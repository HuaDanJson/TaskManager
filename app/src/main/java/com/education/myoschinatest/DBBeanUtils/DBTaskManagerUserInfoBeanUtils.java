package com.education.myoschinatest.DBBeanUtils;

import android.content.Context;

import com.aidebar.greendaotest.gen.DBTaskManagerUserInfoBeanDao;
import com.aidebar.greendaotest.gen.DBUserInfoBeanDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.education.myoschinatest.bean.DBTaskManagerUserInfoBean;

import java.util.List;

/**
 * Created by jason on 2018/1/19.
 */

public class DBTaskManagerUserInfoBeanUtils {

    private DBTaskManagerUserInfoBeanDao dbUserInfoBeanDao;
    private static DBTaskManagerUserInfoBeanUtils dbUserInvestmentUtils = null;

    public DBTaskManagerUserInfoBeanUtils(Context context) {
        dbUserInfoBeanDao = DaoManager.getInstance(context).getNewSession().getDBTaskManagerUserInfoBeanDao();
    }

    public static DBTaskManagerUserInfoBeanUtils getInstance() {
        return dbUserInvestmentUtils;
    }

    public static void Init(Context context) {
        if (dbUserInvestmentUtils == null) {
            dbUserInvestmentUtils = new DBTaskManagerUserInfoBeanUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(DBTaskManagerUserInfoBean dbUserInvestment) {
        dbUserInfoBeanDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<DBTaskManagerUserInfoBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbUserInfoBeanDao.insertOrReplaceInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据操作
     *
     * @param dbUserInvestment
     * @return
     */
    public boolean deleteOneData(DBTaskManagerUserInfoBean dbUserInvestment) {
        boolean flag = false;
        try {
            dbUserInfoBeanDao.delete(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据 ByKey操作
     *
     * @return
     */
    public boolean deleteOneDataByKey(long id) {
        boolean flag = false;
        try {
            dbUserInfoBeanDao.deleteByKey(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中批量删除数据操作
     *
     * @return
     */
    public boolean deleteManData(List<DBTaskManagerUserInfoBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbUserInfoBeanDao.deleteInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库更新数据操作
     *
     * @return
     */
    public boolean updateData(DBTaskManagerUserInfoBean dbUserInvestment) {
        boolean flag = false;
        try {
            dbUserInfoBeanDao.update(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库批量更新数据操作
     *
     * @return
     */
    public boolean updateManData(List<DBTaskManagerUserInfoBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbUserInfoBeanDao.updateInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库查询数据操作
     *
     * @return
     */
    public DBTaskManagerUserInfoBean queryOneData(long id) {
        return dbUserInfoBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<DBTaskManagerUserInfoBean> queryData() {
        return dbUserInfoBeanDao.loadAll();
    }

    /**
     * 完成对数据库DependName条件查询数据操作
     *
     * @return
     */
    public List<DBTaskManagerUserInfoBean> queryDataDependName(String name) {
        return dbUserInfoBeanDao.queryBuilder().where(DBUserInfoBeanDao.Properties.Name.eq(name)).build().list();
    }

    /**
     * 完成对数据库DependUserName条件查询数据操作
     *
     * @return
     */
    public List<DBTaskManagerUserInfoBean> queryDataDependUserName(String userName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBUserInfoBeanDao.Properties.UserName.eq(userName)).build().list();
    }

}
