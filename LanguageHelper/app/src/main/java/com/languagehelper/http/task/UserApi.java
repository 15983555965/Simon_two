package com.languagehelper.http.task;


import com.common.core.internet.Api;
import com.common.core.internet.ApiCallback;
import com.common.core.internet.ApiModel;
import com.common.core.internet.ApiModelList;
import com.languagehelper.http.module.BaseModel;
import com.languagehelper.http.module.User;

/**
 * 收藏相关API
 *
 * @author kevin
 * @version v1.0
 * @since 2014-10/6/14
 */
public class UserApi extends Api {
    /**
     * 登录
     *
     * @param account
     * @param password
     * @param callback
     */
    public static void login(String account, String password, ApiCallback<BaseModel> callback) {
        Api.get(UserApi.class)
                .with("/login")
                .addParams("account", account)
                .addParams("password", password)
                .toModel(new BaseModel())
                .setApiCallback(callback)
                .executeGet();
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param callback
     */
    public static void register(String account, String password, ApiCallback callback) {
        Api.get(UserApi.class)
                .with("/register")
                .addParams("account", account)
                .addParams("password", password)
                .setApiCallback(callback)
                .executeGet();
    }

    /**
     * 各类我的收藏列表

     * @param callback
     */
    public static  void favoriteList(ApiCallback<ApiModelList<User>> callback ) {
        Api.get(UserApi.class)
                .with("/findAllUser")
                .setApiCallback(callback)
                .toModel(new ApiModelList<User>(new User()))
                .executeGet();
    }



}
