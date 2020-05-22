package com.csp.account;

import android.view.View;
import android.widget.Button;

import com.csp.account.base.BaseActivity;
import com.csp.account.database.operate.TAccountOperate;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_db)
    Button mBtnDb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    @OnClick({R.id.btn_db})
    public void onClick(View view) {
        super.onClick(view);
    }

    @Override
    public void onNoFastClick(View view) {
        switch (view.getId()) {
            case R.id.btn_db:
                TAccountOperate.getInstance().printAll();
                break;
        }
    }
}
