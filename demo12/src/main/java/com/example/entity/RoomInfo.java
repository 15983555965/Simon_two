package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房间和用户进如的表
 * Created by Administrator on 2017/3/5.
 */
@Table(name = "room_info")
@Entity
public class RoomInfo {
    @Id
    @GeneratedValue
    private long id;
    /**
     * 房间id
     */
    private long room_id;
    /**
     * 进入房间的用户Id
     */
    private String user_id;

}
