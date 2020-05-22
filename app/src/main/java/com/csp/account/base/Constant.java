package com.csp.account.base;

import com.csp.account.BuildConfig;

public interface Constant {

    /**
     * 调试开关
     */
    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    interface Debug {
        boolean DEBUG = !"release".equals(BuildConfig.FLAVOR) || BuildConfig.DEBUG; // 调试总开关
        boolean LOG_COMMON = DEBUG && true; // 普通日志
        boolean LOG_NETWORK = DEBUG && true; // 网络请求详细日志
        boolean LOG_DATABASE = DEBUG && true; // 数据库详细日志
    }

    /**
     * 网络参数
     */
    interface Network {

    }

    /**
     * 系统常量
     */
    interface System {
        String DATABASE_NAME = "account.db";
    }

    /**
     * 数据传递相关：包括广播、SharedPreferences
     */
    interface TransferKey {
    }

}
