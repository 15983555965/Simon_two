package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 房间基本信息表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "room")
@Entity
public class Room {

    private long id;
    /**
     * 房间Id
     */

    private long room_id;
    /**
     * 房间名称
     */

    private String room_name;
    /**
     * 房间类型
     */

    private int room_type;
    /**
     * 红包最大额度
     */

    private double room_red_packet_max;
    /**
     * 进入房间密码
     */

    private String room_password;
    /**
     * 最大人数
     */

    private int max_user_count;


    private List<User> users;

    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ROOM_ID_NAME")
    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    @Column(name = "ROOM_NAME")
    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    @Column(name = "ROOM_TYPE")
    public int getRoom_type() {
        return room_type;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    @Column(name = "ROOM_RED_PACKET_MAX")
    public double getRoom_red_packet_max() {
        return room_red_packet_max;
    }

    public void setRoom_red_packet_max(double room_red_packet_max) {
        this.room_red_packet_max = room_red_packet_max;
    }

    @Column(name = "ROOM_PASSWORD")
    public String getRoom_password() {
        return room_password;
    }

    public void setRoom_password(String room_password) {
        this.room_password = room_password;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Column(name = "MAX_USER_COUNT")
    public int getMax_user_count() {
        return max_user_count;
    }

    public void setMax_user_count(int max_user_count) {
        this.max_user_count = max_user_count;
    }
}
