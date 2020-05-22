package com.csp.utillib.no_fast_click;

import android.view.View;

/**
 * 防重复点击监听器
 * Created by csp on 2020/01/22
 * Modified by csp on 2020/01/22
 *
 * @version 1.0.0
 */
public abstract class OnNoFastClickListener implements View.OnClickListener {

    @Override
    public final void onClick(View v) {
        if (!NoFastClickUtits.isFastClick(v.getId()))
            onNoFastClick(v);
    }

    public abstract void onNoFastClick(View v);
}
