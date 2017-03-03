package com.languagehelper.ui.home.adapter.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**首页viewPager
 * Created by Administrator on 2017/3/1.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return HomeFragmentFactory.getCount();
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragmentFactory.createFragment(position);
    }
}
