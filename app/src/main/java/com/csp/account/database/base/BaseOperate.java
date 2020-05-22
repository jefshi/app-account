package com.csp.account.database.base;


import android.util.Log;

import androidx.annotation.NonNull;

import com.csp.account.base.Constant;
import com.csp.utillib.EmptyUtil;
import com.csp.utillib.GsonUtil;
import com.csp.utillib.LogCat;

import org.greenrobot.greendao.AbstractDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵亚飞
 * @time 2019/5/27 18:24
 * @Description
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnusedReturnValue"})
public class BaseOperate<T, K> {

    protected AbstractDao<T, K> mDao;

    public BaseOperate(AbstractDao<T, K> dao) {
        this.mDao = dao;
    }

    /**
     * @see AbstractDao#loadAll()
     */
    @NonNull
    public List<T> loadAll() {
        try {
            return mDao.loadAll();
        } catch (Exception e) {
            LogCat.printStackTrace(e);
        }
        return new ArrayList<>();
    }

    /**
     * @see AbstractDao#load(Object)
     */
    public T load(K key) {
        try {
            return mDao.load(key);
        } catch (Exception e) {
            LogCat.printStackTrace(e);
        }
        return null;
    }

    /**
     * 保存
     *
     * @param item 数据
     * @return 是否成功
     */
    public boolean insert(T item) {
        try {
            printAll(item);
            return mDao.insert(item) != -1;
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#insertInTx(Iterable)
     */
    public boolean insertInTx(Iterable<T> entities) {
        try {
            printAll(entities);
            mDao.insertInTx(entities);
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
        return true;
    }

    /**
     * @see AbstractDao#insertInTx(Iterable)
     */
    public void insertInTx(Iterable<T> entities, boolean onebyone) {
        printAll(entities);
        if (entities == null)
            return;

        if (!onebyone)
            insertInTx(entities);

        for (T entity : entities)
            insert(entity);
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#delete(Object)
     */
    public boolean delete(T entity) {
        try {
            printAll(entity);
            mDao.delete(entity);
            return true;
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#deleteInTx(Iterable)
     */
    public boolean deleteInTx(Iterable<T> entities) {
        try {
            printAll(entities);
            mDao.deleteInTx(entities);
            return true;
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#deleteByKey(Object)
     */
    public boolean deleteByKey(K key) {
        try {
            LogCat.v("deleteByKey：" + key); // TODO 调用栈调整
            mDao.deleteByKey(key);
            return true;
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#deleteAll()
     */
    public boolean deleteAll() {
        try {
            LogCat.v("deleteAll"); // TODO 调用栈调整
            mDao.deleteAll();
            return true;
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#update(Object)
     */
    public boolean update(T entity) {
        try {
            printAll(entity);
            mDao.update(entity);
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
        return true;
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#updateInTx(Iterable)
     */
    public boolean updateInTx(Iterable<T> entities) {
        try {
            printAll(entities);
            mDao.updateInTx(entities);
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
        return true;
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#insertOrReplace(Object)
     */
    public boolean insertOrReplace(T entity) {
        try {
            printAll(entity);
            mDao.insertOrReplace(entity);
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
        return true;
    }

    /**
     * @return true: 操作成功
     * @see AbstractDao#insertOrReplaceInTx(Iterable)
     */
    public boolean insertOrReplaceInTx(Iterable<T> entities) {
        try {
            printAll(entities);
            mDao.insertOrReplaceInTx(entities);
        } catch (Exception e) {
            LogCat.printStackTrace(e);
            return false;
        }
        return true;
    }

    /**
     * @param onebyone true: 一条一条操作，非批处理
     * @see AbstractDao#insertOrReplace(Object)
     * @see AbstractDao#insertOrReplaceInTx(Iterable)
     */
    public void insertOrReplaceInTx(Iterable<T> entities, boolean onebyone) {
        printAll(entities);
        if (entities == null)
            return;

        if (!onebyone)
            insertOrReplaceInTx(entities);

        for (T entity : entities)
            insertOrReplace(entity);
    }

    /**
     * 打印所有数据
     */
    public void printAll() {
        List<T> list = loadAll();
        printAll(Log.ERROR, list);
    }

    /**
     * 打印指定表数据
     */
    public void printAll(T datum) {
        List<T> list = new ArrayList<>();
        list.add(datum);
        printAll(Log.VERBOSE, list);
    }

    /**
     * 打印指定表数据
     */
    public void printAll(Iterable<T> data) {
        printAll(Log.VERBOSE, data);
    }

    /**
     * 打印数据库数据
     *
     * @param level 日志级别
     * @param data  数据
     */
    protected void printAll(int level, Iterable<T> data) {
        if (!Constant.Debug.LOG_DATABASE)
            return;

        String tableName = mDao.getTablename();
        if (EmptyUtil.isEmpty(data)) {
            LogCat.log(level, LogCat.DEFAULT_STACK_ID + 1, null, tableName + " is empty");
            return;
        }

        int i = 0;
        for (T datum : data) {
            String explain = tableName + "[" + i + "]";
            String log = GsonUtil.toJson(datum);
            LogCat.log(level, LogCat.DEFAULT_STACK_ID + 1, explain, log);
            ++i;
        }
    }
}
