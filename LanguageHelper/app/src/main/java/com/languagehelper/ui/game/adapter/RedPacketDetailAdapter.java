package com.languagehelper.ui.game.adapter;

import android.content.Context;
import android.view.View;

import com.languagehelper.http.module.RedPacketDetailItem;
import com.languagehelper.ui.game.view.RedPacketDetailItemView;
import com.uikit.adapter.recyclleradapter.BaseAppRecyclerViewAdapter;

/**
 * Created by Administrator on 2017/3/4.
 */

public class RedPacketDetailAdapter extends BaseAppRecyclerViewAdapter<RedPacketDetailItem> {
    public RedPacketDetailAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createItemView(Context mContext, int viewType) {
        return new RedPacketDetailItemView(mContext);
    }

    @Override
    protected void onBindView(View itemView, int position) {
        if (itemView instanceof RedPacketDetailItemView) {
            RedPacketDetailItemView view = (RedPacketDetailItemView) itemView;
            view.setData(getList().get(position));
        }
    }
}
