package com.languagehelper.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.languagehelper.R;
import com.languagehelper.base.MFragment;

/**用户中心
 * Created by Administrator on 2017/3/1.
 */

public class UserFragment extends MFragment {
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.fragment_user);
    }
}
