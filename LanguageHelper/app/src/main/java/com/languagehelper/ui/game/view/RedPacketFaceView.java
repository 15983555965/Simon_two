package com.languagehelper.ui.game.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.languagehelper.R;

/**
 * Created by Administrator on 2017/3/4.
 */

public class RedPacketFaceView extends RelativeLayout {
    private final Context mContext;

    public RedPacketFaceView(Context context) {
        this(context, null);
    }

    public RedPacketFaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedPacketFaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_red_packet, this);

    }
}
