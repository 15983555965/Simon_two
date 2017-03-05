package com.example.entity;

import javax.persistence.*;

/**
 * 红包的份额的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "red_packet_part")
@Entity
public class RedPacketPart {

    private long id;
    /**
     * 红包一份的金额
     */

    private double red_packet_part_amount;
    /**
     * 所属用户的id
     */

    private User user;

    private RedPacket redPacket;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "RED_PACKET_PART_AMOUNT")
    public double getRed_packet_part_amount() {
        return red_packet_part_amount;
    }

    public void setRed_packet_part_amount(double red_packet_part_amount) {
        this.red_packet_part_amount = red_packet_part_amount;
    }

    @JoinColumn(name = "RED_PACKET_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public RedPacket getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.redPacket = redPacket;
    }

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
