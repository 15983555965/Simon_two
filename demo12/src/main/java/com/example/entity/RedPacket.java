package com.example.entity;

import com.example.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 红包基本信息的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "red_packet")
@Entity
public class RedPacket extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 红包金额
     */

    private double redPacketAmount;
    /**
     * 红包类型
     */

    private int redPacketType;
    /**
     * 红包份数
     */
    private int redPacketPartNum;
    /**
     * 发红包的人的id
     */
    private long userId;

    /**
     * 红包的部分
     */
    @Transient
    private List<RedPacketPart> redPacketPartList;
    /**
     * 发红包的人
     */
    @Transient
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRedPacketAmount() {
        return redPacketAmount;
    }

    public void setRedPacketAmount(double redPacketAmount) {
        this.redPacketAmount = redPacketAmount;
    }

    public int getRedPacketType() {
        return redPacketType;
    }

    public void setRedPacketType(int redPacketType) {
        this.redPacketType = redPacketType;
    }

    public int getRedPacketPartNum() {
        return redPacketPartNum;
    }

    public void setRedPacketPartNum(int redPacketPartNum) {
        this.redPacketPartNum = redPacketPartNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<RedPacketPart> getRedPacketPartList() {
        return redPacketPartList;
    }

    public void setRedPacketPartList(List<RedPacketPart> redPacketPartList) {
        this.redPacketPartList = redPacketPartList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
