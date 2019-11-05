package com.csp.account;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.csp.utillib.LogCat;
import com.csp.utillib.Utils;


/**
 * Description: Application
 * Create Date: 2017/7/18
 * Modify Date: 无
 *
 * @author csp
 * @version 1.0.0
 * @since AndroidCases 1.0.0
 */
public class AccountApp extends Application {

    private volatile static AccountApp sApplication;
    private volatile static Context sContext;

    public static Context getContext() {
        return sContext != null
                ? sContext
                : (sContext = getApplication().getApplicationContext());
    }

    public static AccountApp getApplication() {
        if (sApplication != null)
            return sApplication;

        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            sApplication = (AccountApp) app;
        } catch (Exception e) {
            LogCat.printStackTrace(e);
        }
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LogCat.setDebug(BuildConfig.DEBUG);
        Utils.init(this);
    }
}
