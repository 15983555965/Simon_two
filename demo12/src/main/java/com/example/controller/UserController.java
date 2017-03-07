package com.example.controller;

import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.repository.UserRepository;
import com.example.utils.HttpResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/2/17.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 注册
     * @param account
     * @param password
     * @param nickname
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
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
    public BaseEntity login(@RequestParam String account, @RequestParam String password){
        User user = userRepository.findOneByAccountAndPassword(account, password);
        if (user==null){
           return HttpResultUtils.createResult(201,"没有此用户");
        }
        //TODO 生成Token
        return HttpResultUtils.createResult(user);
    }



}
