package com.languagehelper.ui.home.lobby.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.languagehelper.R;

/**
 * 大厅用户信息
 * Created by Administrator on 2017/3/4.
 */

public class LobbyRoomHeadView extends RelativeLayout {

    private final Context mContext;

    public LobbyRoomHeadView(Context context) {
        this(context, null);
    }

    public LobbyRoomHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LobbyRoomHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_lobby_head, this);
    }
}
