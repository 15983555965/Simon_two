package com.languagehelper.ui.game.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.languagehelper.R;
import com.languagehelper.http.module.RedPacketDetailItem;

/**
 * Created by Administrator on 2017/3/4.
 */

public class RedPacketDetailItemView extends RelativeLayout {
    private final Context mContext;

    public RedPacketDetailItemView(Context context) {
        this(context, null);
    }

    public RedPacketDetailItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedPacketDetailItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_red_packet_detail_item, this);
    }

    public void setData(RedPacketDetailItem redPacketDetailItem) {

    }
}
