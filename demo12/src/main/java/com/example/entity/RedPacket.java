package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 红包基本信息的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "red_packet")
@Entity
public class RedPacket {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 属于哪个房间id
     */
    private long room_id;
    /**
     * 红包Id
     */
    private long red_packet_id;
    /**
     * 红包金额
     */
    private double red_packet_amount;
    /**
     * 红包类型
     */
    private int red_packet_type;
    /**
     * 红包份数
     */
    private int red_packet_partes_num;

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

    public long getRed_packet_id() {
        return red_packet_id;
    }

    public void setRed_packet_id(long red_packet_id) {
        this.red_packet_id = red_packet_id;
    }

    public double getRed_packet_amount() {
        return red_packet_amount;
    }

    public void setRed_packet_amount(double red_packet_amount) {
        this.red_packet_amount = red_packet_amount;
    }

    public int getRed_packet_type() {
        return red_packet_type;
    }

    public void setRed_packet_type(int red_packet_type) {
        this.red_packet_type = red_packet_type;
    }

    public int getRed_packet_partes_num() {
        return red_packet_partes_num;
    }

    public void setRed_packet_partes_num(int red_packet_partes_num) {
        this.red_packet_partes_num = red_packet_partes_num;
    }
}
