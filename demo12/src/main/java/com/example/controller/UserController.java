package com.example.controller;

import com.example.entity.Room;
import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;
import com.example.utils.HttpResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/2/17.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private RoomRepository roomRepository;


    public UserController(RoomRepository roomRepository,UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository=userRepository;
    }

    /**
     * 注册
     * @param account
     * @param password
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public BaseEntity register(@RequestParam String account, @RequestParam String password, @RequestParam String nickname) {
        User user = new User();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setPassword(password);
        userRepository.save(user);
        return HttpResultUtils.createResult();
    }

    /**
     * 登录
     * @param account
     * @param password
     */
    @RequestMapping(value = "/login")
    public BaseEntity login(@RequestParam String account, @RequestParam String password){
        User user = userRepository.findOneByAccountAndPassword(account, password);
        if (user==null){
           return HttpResultUtils.createResult(201,"没有此用户");
        }
        //TODO 生成Token
        return HttpResultUtils.createResult(user);
    }

    /**
     * 用户退出房间
     * @param userId
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/exitRoom")
    public BaseEntity exitRoom(@RequestParam(value = "user_id") long userId, @RequestParam(value = "room_id") long roomId){
        User user = userRepository.findOne(userId);
        user.setRoomId(0);
        userRepository.save(user);
        Room room = roomRepository.findOne(roomId);
        if (room!=null){
            int currentUserCount = room.getCurrentUserCount();
            room.setCurrentUserCount(--currentUserCount);
            if (StringUtils.isEmpty(room.getRoomPassword())){
                //公共房间
                if (room.getCurrentUserCount()<=0){
                    //删除公共房间
                    roomRepository.delete(roomId);
                }
            }
        }
        return HttpResultUtils.createResultByCode(BaseEntity.CODE_200);
    }



}
