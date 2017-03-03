package com.languagehelper.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.languagehelper.R;
import com.languagehelper.base.MFragment;
import com.languagehelper.http.module.Lobby;
import com.languagehelper.ui.home.adapter.LobbyAdapter;
import com.uikit.widget.list.RefreshWidget;

import java.util.ArrayList;
import java.util.List;

/**游戏大厅
 * Created by Administrator on 2017/3/1.
 */

public class LobbyFragment extends MFragment {

    private RefreshWidget mRefreshView;
    private List<Lobby> lobbies;

    @Override
    protected void init() {
        super.init();
        initData();
    }

    private void initData() {
        lobbies = new ArrayList<>();
        for (int i=0;i<100;i++){
            Lobby lobby = new Lobby();
            lobby.setTitle(i+"");
            lobbies.add(lobby);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.fragment_lobby);
        mRefreshView = (RefreshWidget) findViewById(R.id.view_refresh);
        LobbyAdapter lobbyAdapter = new LobbyAdapter(getActivity());
        mRefreshView.setAdapter(lobbyAdapter);
        mRefreshView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRefreshView.setHasFixedSize(true);
        lobbyAdapter.setList(lobbies);
    }
}
