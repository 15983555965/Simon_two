package com.example.three_sdk.rong.models;

import com.example.three_sdk.rong.util.GsonUtil;

/**
 * 群组用户信息。
 */
public class GroupUser {
    // 用户 Id。
    String id;

    public GroupUser(String id) {
        this.id = id;
    }

    /**
     * 设置id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupUser.class);
    }
}