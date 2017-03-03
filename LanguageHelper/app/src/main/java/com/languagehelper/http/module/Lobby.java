package com.languagehelper.http.module;

import com.common.core.internet.ApiModel;

import org.json.JSONException;

/**
 * Created by Administrator on 2017/3/1.
 */

public class Lobby extends ApiModel{
    private String title;
    @Override
    public void parseJson(String json) throws JSONException {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
