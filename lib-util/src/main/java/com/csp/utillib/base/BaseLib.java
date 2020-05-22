package com.csp.utillib.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Activity、Fragment 两种页面都实现的常用接口
 * Created by csp on 2016/12/15
 * Modified by csp on 2020/05/22
 *
 * @version 1.0.2
 */
interface BaseLib {

    Context getContext();

    Activity getActivity();

    /**
     * 获取当前页面布局对应的[View]对象
     *
     * @return [View]对象
     */
    View getView();
}
