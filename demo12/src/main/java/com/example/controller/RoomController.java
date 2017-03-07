package com.example.controller;

import com.example.entity.Room;
import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;
import com.example.utils.HttpResultUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
@RestController
public class RoomController {
    public RoomRepository roomRepository;
    public UserRepository userRepository;

    public RoomController(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    /**
     * 请求游戏房间列表
     * @param roomType
     * @param page
     */
    @RequestMapping(value = "/getRoomList",method = RequestMethod.GET)
    public void getRoomList(@RequestParam int roomType,@RequestParam int page){

    }

    /**
     * 创建房间
     * @param user_id  创建者ID
     * @param roomType  房间类型
     * @param maxValue  红包最大值
     * @param minValue  红包最小值
     * @param partNum   红包分为多少份数
     * @param maxUserCount 房间最大人数
     */
    @RequestMapping(value = "/createRoom",method = RequestMethod.GET)
    public BaseEntity createRoom(@RequestParam long user_id, @RequestParam int roomType, @RequestParam String password, @RequestParam double maxValue, @RequestParam double minValue, @RequestParam int partNum, @RequestParam int maxUserCount){
        Room room = new Room();
        room.setUserId(user_id);
        room.setRoomType(roomType);
        room.setMaxValue(maxValue);
        room.setMinValue(minValue);
        room.setRoomPassword(password);
        room.setCurrentUserCount(1);
        room.setMaxUserCount(maxUserCount);
        room.setPartNum(partNum);
        room = roomRepository.save(room);
        User user = userRepository.findOne(user_id);
        user.setRoomId(room.getId());
        return HttpResultUtils.createResult(room);
    }

    /**
     * 进入房间
     * @param userId
     * @param roomId
     * @param password
     * @return
     */
    @RequestMapping(value = "/joinRomm",method = RequestMethod.GET)
    public BaseEntity joinRoom(@RequestParam long userId, @RequestParam long roomId,@RequestParam String password){
        Room room = roomRepository.findOne(roomId);
        if (room!=null&&room.isCanJoinRoom(password)){
            User user = userRepository.findOne(userId);
            user.setRoomId(roomId);
            room.addOneUser();
            roomRepository.save(room);
            return HttpResultUtils.createResult();
        }
        return HttpResultUtils.createResult(BaseEntity.CODE_3022,"进入房间失败");
    }

    /**
     * 获取房间信息
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/roomInfo",method = RequestMethod.GET)
    public BaseEntity getRoomInfo(@RequestParam long roomId){
        Room room = roomRepository.findOne(roomId);
        if (room==null){
            return HttpResultUtils.createResult(BaseEntity.CODE_3021,"获取房间信息失败");
        }
        List<User> users = userRepository.findAllByRoomId(room.getId());
        room.setUsers(users);
        return room;
    }

}
