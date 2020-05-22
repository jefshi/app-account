package com.csp.account.base;

import com.csp.utillib.base.BaseLibActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * BaseActivity
 * Created by csp on 2019/12/15.
 * Modified by csp on 2019/03/21.
 *
 * @version 1.0.4
 */
public abstract class BaseActivity extends BaseLibActivity {

    protected Unbinder unbinder;

    @Override
    protected void setContentViewAfter() {
        super.setContentViewAfter();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }
}
