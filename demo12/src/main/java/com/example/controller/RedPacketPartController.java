package com.example.controller;

import com.example.repository.RedPacketPartRepository;
import com.example.repository.RedPacketRepository;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;

/**
 * Created by Administrator on 2017/3/8.
 */
public class RedPacketPartController {
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private RedPacketRepository redPacketRepository;
    private RedPacketPartRepository redPacketPartRepository;

    public RedPacketPartController(UserRepository userRepository, RoomRepository roomRepository, RedPacketRepository redPacketRepository, RedPacketPartRepository redPacketPartRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.redPacketRepository = redPacketRepository;
        this.redPacketPartRepository = redPacketPartRepository;
    }
}

