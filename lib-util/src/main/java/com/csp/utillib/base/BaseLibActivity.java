package com.csp.utillib.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.csp.utillib.no_fast_click.NoFastClickUtits;


/**
 * 跟业务无关的 BaseActivity
 * Created by csp on 2016/12/15
 * Modified by csp on 2020/05/22
 *
 * @version 1.0.3
 */
public abstract class BaseLibActivity extends AppCompatActivity
        implements View.OnClickListener, BaseLib {

    private boolean mInitialized; // true: 页面初始化过数据
    private boolean mDestroyed = false;
    private boolean mFront = false; // 是否在前台

    public boolean isInitialized() {
        return mInitialized;
    }

    public boolean isFront() {
        return mFront;
    }

    @Override
    @SuppressLint("ObsoleteSdkInt")
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return super.isDestroyed();
        } else {
            return mDestroyed || isFinishing();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentViewBefore();
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        } else {
            setContentView();
        }
        setContentViewAfter();

        init();
    }

    /**
     * @return 布局资源 ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化界面
     */
    protected abstract void init();

    /**
     * @see Activity#setContentView(View)
     */
    protected void setContentView() {
    }

    protected void setContentViewBefore() {
    }

    protected void setContentViewAfter() {
    }

    /**
     * 前台与销毁状态
     */
    @Override
    protected void onResume() {
        super.onResume();
        mFront = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFront = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDestroyed = true;
    }

    /**
     * BaseLib 接口实现
     */
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public View getView() {
        return getWindow().getDecorView();
    }

    /**
     * 防重复点击
     */
    @Override
    public void onClick(View view) {
        if (!isFastClick(view.getId()))
            onNoFastClick(view);
    }

    public boolean isFastClick(@IdRes int viewId) {
        return NoFastClickUtits.isFastClick(viewId);
    }

    public void onNoFastClick(View view) {
    }

    /**
     * Android点击EditText文本框之外任何地方隐藏键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev) && v != null) {
                InputMethodManager imm = (InputMethodManager)
                        this.getSystemService(Activity.INPUT_METHOD_SERVICE);

                if (imm != null && imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (!(v instanceof EditText))
            return false;

        // 获取输入框当前的 location 位置
        int[] leftTop = {0, 0};
        v.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        int bottom = top + v.getHeight();
        int right = left + v.getWidth();

        return !(event.getX() > left)
                || !(event.getX() < right)
                || !(event.getY() > top)
                || !(event.getY() < bottom);
    }

    /**
     * 正常、异常页面显示与切换用
     */
    public void setInitialized(boolean initialized) {
        if (!mInitialized && initialized) {
            mInitialized = true;
        }
    }

    /**
     * @param showed true：显示数据存在的页面（正常情况）
     */
    public void showContent(boolean showed) {
        setInitialized(showed);
        if (showed) {
            showErrorNetWork(null, false);
            showErrorNoData(false);
        }
    }

    /**
     * @param showed true：显示数据为空的界面
     */
    public void showErrorNoData(boolean showed) {
        setInitialized(showed);
        if (showed) {
            showContent(false);
            showErrorNetWork(null, false);
        }
    }

    /**
     * @param throwable 异常信息
     * @param showed    true：显示网络错误的页面
     */
    public void showErrorNetWork(Throwable throwable, boolean showed) {
        if (showed) {
            showContent(false);
            showErrorNoData(false);
        }
    }

    /**
     * Fragment 页面切换
     *
     * @param resId    fragment 的容器控件ID
     * @param fragment 页面对象
     * @param tag      fragment 的 TAG 标志
     */
    public void replaceFragment(@IdRes int resId, @NonNull Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(resId, fragment, tag);
        ft.commit();
    }

    /**
     * @param resId  包含 %s 的字符串资源
     * @param values 填充 %s 的值
     * @return 填充好数据的字符串
     * @see String#format(String, Object...)
     */
    public String format(@StringRes int resId, Object... values) {
        String text = getString(resId);
        if (values == null)
            return text;

        for (int i = 0; i < values.length; i++) {
            if (values[i] == null)
                values[i] = "";
        }
        return String.format(text, values);
    }
}