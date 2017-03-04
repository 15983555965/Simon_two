package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**用户信息表
 * Created by Administrator on 2017/2/17.
 */
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String portrait;
    /**
     * 注册时间
     */
    private long register_time;
    /**
     * 金币
     */
    private double gold;
    /**
     * 绑定金币
     */
    private double binding_gold;
    /**
     * 用户类型
     */
    private int user_type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public long getRegister_time() {
        return register_time;
    }

    public void setRegister_time(long register_time) {
        this.register_time = register_time;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getBinding_gold() {
        return binding_gold;
    }

    public void setBinding_gold(double binding_gold) {
        this.binding_gold = binding_gold;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
