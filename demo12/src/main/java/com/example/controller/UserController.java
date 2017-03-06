package com.example.controller;

import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.repository.UserRepository;
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

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public BaseEntity register(@RequestParam String account, @RequestParam String password, @RequestParam String nickname) {
        User user = new User();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setPassword(password);
        userRepository.save(user);
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setInfo("success");
        baseEntity.setStatus(200);
        return baseEntity;
    }

}
