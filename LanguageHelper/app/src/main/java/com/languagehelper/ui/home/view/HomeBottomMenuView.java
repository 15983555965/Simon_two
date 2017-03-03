package com.languagehelper.ui.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.languagehelper.R;

/**
 * Created by Administrator on 2017/3/1.
 */

public class HomeBottomMenuView extends RelativeLayout implements View.OnClickListener {
    public static int TAB_LOBBY=R.id.tv_home_bottom_menu_lobby;
    public static int TAB_CREATE=R.id.tv_home_bottom_menu_create;
    public static int TAB_USER=R.id.tv_home_bottom_menu_user;
    private final Context mContext;
    private TextView mLobbyView;
    private TextView mCreatView;
    private TextView mUserView;
    private LinearLayout mBottomView;
    private OnHomeBottomClickListener listener;

    public HomeBottomMenuView(Context context) {
        this(context,null);
    }

    public HomeBottomMenuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeBottomMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_home_bottom_menu, this);
        mBottomView = (LinearLayout) findViewById(R.id.ll_homm_bottom_menu);
        mLobbyView = (TextView) findViewById(R.id.tv_home_bottom_menu_lobby);
        mCreatView = (TextView) findViewById(R.id.tv_home_bottom_menu_create);
        mUserView = (TextView) findViewById(R.id.tv_home_bottom_menu_user);
        mLobbyView.setOnClickListener(this);
        mCreatView.setOnClickListener(this);
        mUserView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_home_bottom_menu_create:
            case R.id.tv_home_bottom_menu_lobby:
            case R.id.tv_home_bottom_menu_user:
                selectedTab(v.getId());
                break;
        }
    }

    private void selectedTab(int tabId) {
        int childCount = mBottomView.getChildCount();
        for (int i=0;i<childCount;i++){
            View child = mBottomView.getChildAt(i);
            if (child.getId()==tabId){
                child.setSelected(true);
                if (listener!=null){
                    listener.onClick(child,tabId,i);
                }
            }else{
                child.setSelected(false);
            }

        }
    }

    public void setOnHomeBottomClickListener(OnHomeBottomClickListener listener){
        this.listener=listener;
    }
    public interface OnHomeBottomClickListener{
        void onClick(View v,int type,int position);
    }
}
