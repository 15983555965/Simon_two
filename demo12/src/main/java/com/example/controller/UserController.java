package com.example.controller;

import com.example.entity.Room;
import com.example.config.RongYunConfig;
import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;
import com.example.three_sdk.rong.RongCloud;
import com.example.three_sdk.rong.models.CodeSuccessReslut;
import com.example.three_sdk.rong.models.TokenReslut;
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
    private RongCloud rongCloud;
    private RoomRepository roomRepository;



    public UserController(RoomRepository roomRepository,UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository=userRepository;
            initData();
    }


    private void initData() {
        rongCloud = RongCloud.getInstance(RongYunConfig.appKey, RongYunConfig.appSecret);
    }

    /**
     * 注册
     * @param account
     * @param password
     * @param nickname
     * @return
     */

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public BaseEntity register(@RequestParam String account, @RequestParam String password, @RequestParam String nickname) throws Exception {
        User user = new User();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setPassword(password);
        TokenReslut token = rongCloud.user.getToken(user.getId() + "", user.getNickname(), "http://www.rongcloud.cn/images/logo.png");
        user.setToken(token.getToken());
        userRepository.save(user);
        return HttpResultUtils.createResult();
    }

    /**
     * 登录
     * @param account
     * @param password
     */
    @RequestMapping(value = "/login")
    public BaseEntity login(@RequestParam String account, @RequestParam String password,@RequestParam String token){
        User user = null;
        if (StringUtils.isEmpty(token)) {
            user = userRepository.findOneByAccountAndPassword(account, password);
        }else{
            user = userRepository.findAllByToken(token);
        }

        if (user == null) {
            return HttpResultUtils.createResultByCode(BaseEntity.CODE_3101);
        }
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
    @RequestMapping("/updateProtrait")
    public BaseEntity updatePortrait(@RequestParam long user_id, @RequestParam String nick_name, @RequestParam String portrait) throws Exception {
        User user = userRepository.findOne(user_id);
        user.setNickname(nick_name);
        user.setPortrait(portrait);
        CodeSuccessReslut userRefreshResult = rongCloud.user.refresh(user.getId()+"", user.getNickname(), user.getPortrait());
        if (userRefreshResult.getCode()!=BaseEntity.CODE_200){
            return HttpResultUtils.createResultByCode(BaseEntity.CODE_3102);
        }
        userRepository.save(user);
        return HttpResultUtils.createResultByCode(BaseEntity.CODE_200);
    }


}
