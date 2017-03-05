package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**用户信息表
 * Created by Administrator on 2017/2/17.
 */
@Table(name = "user")
@Entity
public class User {

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
    /**
     * 进入得房间
     */

    private Room room;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NICK_NAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "PORTRAIT")
    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Column(name = "REGISTER_TIME")
    public long getRegister_time() {
        return register_time;
    }

    public void setRegister_time(long register_time) {
        this.register_time = register_time;
    }

    @Column(name = "GOLD")
    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    @Column(name = "BINDING_GOLD")
    public double getBinding_gold() {
        return binding_gold;
    }

    public void setBinding_gold(double binding_gold) {
        this.binding_gold = binding_gold;
    }

    @Column(name = "USER_TYPE")
    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    @JoinColumn(name = "ROOM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
