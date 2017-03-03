// Copy right WeCook Inc.
package com.uikit.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.uikit.util.ActivityManagerTaskUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 抽象Activity
 *
 * @author kevin
 * @version v1.0
 * @since 2014-Sep 17, 2014
 */
public abstract class BaseActivity extends FragmentActivity implements IActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManagerTaskUtils.add(this);
        init();
        initView();
        initBaseActionBar();
        load();
    }

    protected abstract void load();


    protected abstract void init();

    protected abstract void initView();

    public abstract View initActionBar();

    protected void initBaseActionBar() {
        ActionBar mActionBar = getActionBar();
        if (mActionBar == null) {
            return;
        }
        // 隐藏Home图标和Title文字
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayUseLogoEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setHomeButtonEnabled(false);
        // 隐藏Label标签
        mActionBar.setDisplayShowTitleEnabled(false);
        // 对ActionBar启用自定义View
        mActionBar.setDisplayShowCustomEnabled(true);
        View actionBarView = initActionBar();
        mActionBar.setCustomView(actionBarView);
        if (actionBarView == null) {
            mActionBar.hide();
        } else {
            mActionBar.show();
        }
    }

    @Override
    protected void onDestroy() {
        ActivityManagerTaskUtils.remove(this);
        super.onDestroy();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onFinish() {

    }
}
