package com.languagehelper.http.module;

import com.common.core.internet.ApiModel;

import org.json.JSONException;

/**
 * Created by Administrator on 2017/3/4.
 */

public class RedPacketDetailItem extends BaseModel {
    private String headimgPath;
    private String userName;
    private String time;
    private String money;

    @Override
    public void parseJson(String json) throws JSONException {

    }

    public String getHeadimgPath() {
        return headimgPath;
    }

    public void setHeadimgPath(String headimgPath) {
        this.headimgPath = headimgPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
