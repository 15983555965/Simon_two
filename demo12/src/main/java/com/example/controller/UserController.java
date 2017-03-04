package com.example.controller;

import com.example.entity.User;
import com.example.entity.request.AddUserRequest;
import com.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @RequestMapping(method = RequestMethod.GET,value = "/findAllUser")
    public List<User> findAllUser(){
        return userRepository.findAll();
    }
    @RequestMapping(method = RequestMethod.GET,value ="/addUser")
    public void addUser(@RequestParam String name,@RequestParam int age){
        User user = new User();

        userRepository.save(user);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/addUser")
    public void addUser(@RequestBody AddUserRequest addUserRequest){
        User user = new User();
        userRepository.save(user);
    }

}
