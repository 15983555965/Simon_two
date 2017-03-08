package com.example.controller;

import com.example.entity.Room;
import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.entity.base.HttpResult;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;
import com.example.utils.HttpResultUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public BaseEntity getRoomList(@RequestParam int roomType, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "15") int size){
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            Pageable pageable = new PageRequest(page, size, sort);
            return HttpResultUtils.createResult(roomRepository.findAll(pageable));
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
    public BaseEntity createRoom(@RequestParam(value = "user_id") long user_id, @RequestParam(value = "room_type") int roomType,
                                 @RequestParam(value = "password") String password, @RequestParam(value = "max_value") double maxValue,
                                 @RequestParam(value = "min_value") double minValue, @RequestParam(value = "part_num") int partNum,
                                 @RequestParam(value = "max_user_count") int maxUserCount){
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
    @RequestMapping(value = "/joinRoom",method = RequestMethod.GET)
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
        return HttpResultUtils.createResult(room);
    }

    /**
     * 删除房间（私人房间才需要创建者删除）
     *  @param userId
     * @param roomId
     */
    @RequestMapping(value = "/deleteRoom",method = RequestMethod.GET)
    public BaseEntity deleteRoom(@RequestParam long userId, @RequestParam long roomId){
        User user = userRepository.findOne(userId);
        Room room = roomRepository.findOne(roomId);
        long createUserId = room.getUserId();
        if (createUserId!=userId){
            //不是创建房间的人
            return HttpResultUtils.createResultByCode(BaseEntity.CODE_3023);
        }
        user.setRoomId(0);
        userRepository.save(user);
        roomRepository.delete(roomId);
        return HttpResultUtils.createResultByCode(BaseEntity.CODE_200);
    }

}
