package com.example.entity;

import com.example.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 房间基本信息表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "room")
@Entity
public class Room extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 房间Id
     */

    private long roomId;
    /**
     * 房间名称
     */

    private String roomName;
    /**
     * 房间类型
     */

    private int roomType;
    /**
     * 红包最大额度
     */

    private double roomRedPacketMax;
    /**
     * 进入房间密码
     */

    private String roomPassword;
    /**
     * 最大人数
     */

    private int maxUserCount;

    /**
     * 房间的创建人id
     */
    private long userId;
    /**
     * 房间的创建者
     */
    @Transient
    private User createUser;
    /**
     * 房间的所有人
     */
    @Transient
    private List<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public double getRoomRedPacketMax() {
        return roomRedPacketMax;
    }

    public void setRoomRedPacketMax(double roomRedPacketMax) {
        this.roomRedPacketMax = roomRedPacketMax;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
