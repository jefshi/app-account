package com.csp.utillib.no_fast_click;


import android.util.SparseLongArray;

import androidx.annotation.IdRes;

import com.csp.utillib.LogCat;

/**
 * 防重复点击
 * Created by csp、gcc on 2019/12/31
 * Modified by csp、gcc on 2019/12/31
 *
 * @version 1.0.0
 */
public class NoFastClickUtits {
    private static int sSpaceTime = 300;
    private static SparseLongArray sViews = new SparseLongArray();

    public static void setSpaceTime(int spaceTime) {
        sSpaceTime = spaceTime;
    }

    public static boolean isFastClick(@IdRes int viewId) {
        return isFastClick(viewId, sSpaceTime);
    }

    public static boolean isFastClick(@IdRes int viewId, long spaceTime) {
        long currentTime = System.currentTimeMillis();
        long lastClickTime = sViews.get(viewId);
        boolean firstRegister = lastClickTime == 0;
        if (!firstRegister) {
            LogCat.i("view id = [%s], lastClickTime is [%s]", viewId, lastClickTime);
        }
        boolean fastClick = !firstRegister
                && currentTime - lastClickTime < spaceTime;

        if (!fastClick)
            sViews.put(viewId, currentTime);

        return fastClick;
    }
}
