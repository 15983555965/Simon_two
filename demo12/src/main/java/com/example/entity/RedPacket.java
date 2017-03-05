package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 红包基本信息的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "red_packet")
@Entity
public class RedPacket {

    private long id;

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

    private int red_packet_part_num;
    /**
     *
     */
    private List<RedPacketPart> redPacketPartList;
    private User user;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "RED_PACKET_AMOUNT")
    public double getRed_packet_amount() {
        return red_packet_amount;
    }

    public void setRed_packet_amount(double red_packet_amount) {
        this.red_packet_amount = red_packet_amount;
    }

    @Column(name = "RED_PACKET_TYPE")
    public int getRed_packet_type() {
        return red_packet_type;
    }

    public void setRed_packet_type(int red_packet_type) {
        this.red_packet_type = red_packet_type;
    }

    @Column(name = "RED_PACKET_PART_NUM")
    public int getRed_packet_part_num() {
        return red_packet_part_num;
    }

    public void setRed_packet_part_num(int red_packet_part_num) {
        this.red_packet_part_num = red_packet_part_num;
    }

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "redPacket")
    public List<RedPacketPart> getRedPacketPartList() {
        return redPacketPartList;
    }

    public void setRedPacketPartList(List<RedPacketPart> redPacketPartList) {
        this.redPacketPartList = redPacketPartList;
    }
}
