package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 红包的份额的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "red_packet_part")
@Entity
public class RedPacketPart {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 所属红包的Id
     */
    private long red_packet_id;
    /**
     * 红包一份的金额
     */
    private double red_packet_part_amount;
    /**
     * 所属用户的id
     */
    private String user_id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRed_packet_id() {
        return red_packet_id;
    }

    public void setRed_packet_id(long red_packet_id) {
        this.red_packet_id = red_packet_id;
    }

    public double getRed_packet_part_amount() {
        return red_packet_part_amount;
    }

    public void setRed_packet_part_amount(double red_packet_part_amount) {
        this.red_packet_part_amount = red_packet_part_amount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
