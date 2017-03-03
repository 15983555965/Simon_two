// Copy right WeCook Inc.
package com.uikit.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 抽象Fragment
 *
 * @author kevin
 * @version v1.0
 * @since 2014-Sep 17, 2014
 */
public abstract class BaseFragment extends Fragment implements IFragment {

    private View mRootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        init();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        if (mRootView!=null) {
            return mRootView;
        }else{
            return super.onCreateView(inflater,container,savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        load();
    }



    protected abstract void init();
    protected abstract void initView();
    protected abstract void load();

    public void setContentView(int resId){
        View rootView = LayoutInflater.from(getActivity()).inflate(resId, null);
        setContentView(rootView);
    }
    public void setContentView(View view){
        mRootView = view;
    }

    public View findViewById(int resid){
        View view = null;
        if (mRootView!=null){
            view = mRootView.findViewById(resid);
        }
        return view;
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
