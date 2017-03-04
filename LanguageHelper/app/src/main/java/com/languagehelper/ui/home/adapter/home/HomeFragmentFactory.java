package com.languagehelper.ui.home.adapter.home;

import com.languagehelper.base.MFragment;
import com.languagehelper.ui.home.create.fragment.CreateFragment;
import com.languagehelper.ui.home.lobby.fragment.LobbyFragment;
import com.languagehelper.ui.home.user.fragment.UserFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/1.
 */

public class HomeFragmentFactory {
    public static final int TAB_LOBBY = 0;
    public static final int TAB_CREATE = 1;
    public static final int TAB_USER = 2;
    private static Map<Integer, MFragment> mFragmentMap = new HashMap();

    public static MFragment createFragment(int index) {
        MFragment fragment = mFragmentMap.get(index);
        if (fragment == null) {
            switch (index) {
                case TAB_LOBBY:
                    fragment = new LobbyFragment();
                    break;
                case TAB_CREATE:
                    fragment = new CreateFragment();
                    break;
                case TAB_USER:
                    fragment = new UserFragment();
                    break;
            }
            mFragmentMap.put(index, fragment);
        }

        return fragment;
    }

    public static int getCount() {
        return 3;
    }
}
