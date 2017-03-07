package com.languagehelper.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.common.core.internet.ApiCallback;
import com.common.core.internet.ApiModelList;
import com.languagehelper.R;
import com.languagehelper.base.MActivity;
import com.languagehelper.http.module.User;
import com.languagehelper.http.task.UserApi;
import com.languagehelper.ui.home.adapter.home.HomeViewPagerAdapter;
import com.languagehelper.ui.home.view.HomeBottomMenuView;
import com.uikit.widget.TitleBar;

public class MainActivity extends MActivity {

    private TitleBar mTitleBar;
    private ViewPager mViewPager;
    private HomeBottomMenuView mBottomMenuView;
    private HomeViewPagerAdapter mHomeViewPagerAdapter;

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_home_viewpager);
        mBottomMenuView = (HomeBottomMenuView) findViewById(R.id.view_home_bottom_menu);

        mBottomMenuView.setOnHomeBottomClickListener(new HomeBottomMenuView.OnHomeBottomClickListener() {
            @Override
            public void onClick(View v, int type, int position) {
                int currentItem = mViewPager.getCurrentItem();
                if (currentItem!=position){
                    mViewPager.setCurrentItem(position,false);
                }
            }
        });

        mHomeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mHomeViewPagerAdapter);
    }

    @Override
    public View initActionBar() {
        mTitleBar = new TitleBar(this);
        mTitleBar.setTitle("首页");
        return mTitleBar;
    }

    @Override
    protected void load() {
        super.load();
        UserApi.favoriteList(new ApiCallback<ApiModelList<User>>() {
            @Override
            public void onResult(ApiModelList<User> result) {

            }
        });
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
