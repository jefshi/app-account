package com.csp.account.database.operate;


import android.util.Log;

import com.csp.account.AccountApp;
import com.csp.account.database.base.BaseOperate;
import com.csp.account.database.bean.TAccount;

import java.util.List;

/**
 * @author 赵亚飞
 * @time 2019/5/28 14:45
 * @Description 单人聊天数据操作
 */
public class TAccountOperate extends BaseOperate<TAccount, String> {

    @Override
    public void printAll() {
        List<TAccount> list = mDao.queryBuilder()
                .build()
                .list();

        printAll(Log.ERROR, list);
    }

    private TAccountOperate() {
        super(AccountApp.getApplication().getDaoSession().getTAccountDao());
    }

    private static class Instance {
        private static final TAccountOperate instance = new TAccountOperate();
    }

    public static TAccountOperate getInstance() {
        return Instance.instance;
    }
}
