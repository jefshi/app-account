package com.csp.account.database.helper;

import android.content.Context;

import com.csp.account.database.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;


/**
 * @author 赵亚飞
 * @time 2019/5/27 18:17
 * @Description 书库创建升级
 */
public class DevOpenHelper extends DaoMaster.DevOpenHelper {

    public DevOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        DaoMaster.createAllTables(db, true);
    }

    @Override
    public void onUpgrade(final Database db, int oldVersion, int newVersion) {
        MigrateHelper.migrate(db, new MigrateHelper.OnCreateAllTablesListener() {
            @Override
            public void onCreateAllTables() {
                DaoMaster.createAllTables(db, false);
            }
        });
    }
}
