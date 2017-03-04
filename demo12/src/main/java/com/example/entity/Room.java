package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房间基本信息表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "room")
@Entity
public class Room {
    @Id
    @GeneratedValue
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
     * 红包额度
     */
    private double red_packet_amount;
    /**
     * 进入房间密码
     */
    private String room_password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_type() {
        return room_type;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    public double getRed_packet_amount() {
        return red_packet_amount;
    }

    public void setRed_packet_amount(double red_packet_amount) {
        this.red_packet_amount = red_packet_amount;
    }
}
