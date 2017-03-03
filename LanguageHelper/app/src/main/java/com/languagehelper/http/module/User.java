package com.languagehelper.http.module;

import com.common.core.internet.ApiModel;
import com.common.modules.BaseModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/28.
 */

public class User extends ApiModel {
    private String name;
    private int age;

    @Override
    public void parseJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        name = jsonObject.optString("name");
        age = jsonObject.optInt("age", 0);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
