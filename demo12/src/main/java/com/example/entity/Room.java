package com.example.entity;

import com.example.entity.base.BaseEntity;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * 房间基本信息表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "room")
@Entity
public class Room extends BaseEntity {
    /**
     * 接龙
     */
    @Transient
    public static final int TYPE_CONNECT=1;
    /**
     * 扫雷
     */
    @Transient
    public static final int TYPE_MINESWEEPER=2;
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
     * 房间类型：1，红包接龙 2红包扫雷
     */

    private int roomType;
    /**
     * 红包最大额度
     */

    private double maxValue;
    /**
     * 红包最小额度
     */
    private double minValue;
    /**
     * 进入房间密码
     */

    private String roomPassword;
    /**
     * 最大人数
     */

    private int maxUserCount;
    /**
     * 当前房间人数
     */
    private int currentUserCount;

    /**
     * 房间的创建人id
     */
    private long userId;
    /**
     * 红包分为多少份数
     */
    private int partNum;
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

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public int getCurrentUserCount() {
        return currentUserCount;
    }

    public void setCurrentUserCount(int currentUserCount) {
        this.currentUserCount = currentUserCount;
    }

    public int getPartNum() {
        return partNum;
    }

    public void setPartNum(int partNum) {
        this.partNum = partNum;
    }
    public boolean isCanJoinRoom(){
        return isCanJoinRoom(null);
    }
    public boolean isCanJoinRoom(String password) {
        if (!StringUtils.isEmpty(roomPassword)){
            //需要密码登录
            if (!roomPassword.equals(password)){
                return false;
            }
        }

        if (maxUserCount<=currentUserCount){
            return false;
        }
        return true;
    }
}
