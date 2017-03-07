package com.example.entity;

import com.example.entity.base.BaseEntity;

import javax.persistence.*;

/**用户信息表
 * Created by Administrator on 2017/2/17.
 */
@Table(name = "user")
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

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
    /**
     * 用户手机号码
     */
    private String phone;
    /**
     * 令牌Token
     */
    private String token;
    /**
     * 房间Id
     */
    private long roomId;

    /**
     * 进入得房间
     */
    @Transient
    private Room room;


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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
