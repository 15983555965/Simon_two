package com.example.entity;

import com.example.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * 红包的份额的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "red_packet_part")
@Entity
public class RedPacketPart extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 红包一份的金额
     */

    private double redPacketPartAmount;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 所属红包的id
     */
    private long redPacketId;
    /**
     * 所属用户的id
     */
    @Transient
    private User user;
    /**
     * 所属红包
     */
    @Transient
    private RedPacket redPacket;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRedPacketPartAmount() {
        return redPacketPartAmount;
    }

    public void setRedPacketPartAmount(double redPacketPartAmount) {
        this.redPacketPartAmount = redPacketPartAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RedPacket getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.redPacket = redPacket;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(long redPacketId) {
        this.redPacketId = redPacketId;
    }
}
