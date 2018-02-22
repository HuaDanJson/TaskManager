package com.education.myoschinatest.utils;

import android.content.Context;

import com.aidebar.greendaotest.gen.DBTaskBeanDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.education.myoschinatest.bean.DBTaskBean;

import java.util.List;

/**
 * Created by jason on 2018/1/27.
 */

public class DBTaskBeanUtils {

    private DBTaskBeanDao dbUserInvestmentDao;
    private static DBTaskBeanUtils dbUserInvestmentUtils = null;

    public DBTaskBeanUtils(Context context) {
        dbUserInvestmentDao = DaoManager.getInstance(context).getNewSession().getDBTaskBeanDao();
    }

    public static DBTaskBeanUtils getInstance() {
        return dbUserInvestmentUtils;
    }

    public static void Init(Context context) {
        if (dbUserInvestmentUtils == null) {
            dbUserInvestmentUtils = new DBTaskBeanUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(DBTaskBean dbUserInvestment) {
        dbUserInvestmentDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<DBTaskBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbUserInvestmentDao.insertOrReplaceInTx(dbUserInvestmentList);
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
    public boolean deleteOneData(DBTaskBean dbUserInvestment) {
        boolean flag = false;
        try {
            dbUserInvestmentDao.delete(dbUserInvestment);
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
            dbUserInvestmentDao.deleteByKey(id);
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
    public boolean deleteManData(List<DBTaskBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbUserInvestmentDao.deleteInTx(dbUserInvestmentList);
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
    public boolean deleteAllData() {
        boolean flag = false;
        try {
            dbUserInvestmentDao.deleteAll();
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
    public boolean updateData(DBTaskBean dbUserInvestment) {
        boolean flag = false;
        try {
            dbUserInvestmentDao.update(dbUserInvestment);
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
    public boolean updateManData(List<DBTaskBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            dbUserInvestmentDao.updateInTx(dbUserInvestmentList);
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
    public DBTaskBean queryOneData(long id) {
        return dbUserInvestmentDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<DBTaskBean> queryAllData() {
        return dbUserInvestmentDao.loadAll();
    }

    /**
     * 完成对数据库条件查询数据操作
     *
     * @return
     */
    public List<DBTaskBean> queryDataDependName(String name) {
        return dbUserInvestmentDao.queryBuilder().where(DBTaskBeanDao.Properties.TaskName.eq(name)).build().list();
    }

    /**
     * 完成对数据库条件模糊查询数据操作
     *
     * @return
     */
    public List<DBTaskBean> queryDataLikeName(String name) {
        return dbUserInvestmentDao.queryBuilder().where(DBTaskBeanDao.Properties.TaskName.like("%" + name + "%")).build().list();
    }
}


