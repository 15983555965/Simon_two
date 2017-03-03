package com.languagehelper.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.languagehelper.http.module.Lobby;
import com.uikit.adapter.recyclleradapter.BaseAppRecyclerViewAdapter;

/**
 * Created by Administrator on 2017/3/1.
 */

public class LobbyAdapter extends BaseAppRecyclerViewAdapter<Lobby> {
    public LobbyAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createItemView(Context mContext, int viewType) {
        return new TextView(mContext);
    }

    @Override
    protected void onBindView(View itemView, int position) {
        TextView view= (TextView) itemView;
        view.setText(getList().get(position).getTitle());
    }
}
