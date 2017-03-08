package com.example.controller;

import com.example.entity.RedPacket;
import com.example.entity.RedPacketPart;
import com.example.entity.User;
import com.example.entity.base.BaseEntity;
import com.example.repository.RedPacketPartRepository;
import com.example.repository.RedPacketRepository;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;
import com.example.utils.HttpResultUtils;
import com.example.utils.RedPacketHelp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
public class RedPacketController {
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private RedPacketRepository redPacketRepository;
    private RedPacketPartRepository redPacketPartRepository;

    public RedPacketController(UserRepository userRepository, RoomRepository roomRepository, RedPacketRepository redPacketRepository, RedPacketPartRepository redPacketPartRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.redPacketRepository = redPacketRepository;
        this.redPacketPartRepository = redPacketPartRepository;
    }

    @RequestMapping(value = "/sendRedPacket")
    public BaseEntity sendRedPacket(@RequestParam(value = "user_id") long userId, @RequestParam(value = "room_id") long room_id,
                                    @RequestParam(value = "red_packet_amount") double redPcketAmount, @RequestParam(value = "red_packet_part_num") int redPacketPartNum,
                                    @RequestParam(value = "red_packet_type") int redPacketType){
        RedPacket redPacket = createRedPacket(userId, room_id, redPcketAmount, redPacketPartNum, redPacketType);

        RedPacket saveRedPacket = redPacketRepository.save(redPacket);
        List<RedPacketPart> redPacketParts = clipRedPcket(saveRedPacket);
        for (RedPacketPart item:redPacketParts){
            redPacketPartRepository.save(item);
        }
        return HttpResultUtils.createResult(redPacket);
    }

    /**
     * 分割红包
     * @param redPacket
     * @return
     */
    private List<RedPacketPart> clipRedPcket(RedPacket redPacket) {
        double redPacketAmount = redPacket.getRedPacketAmount();
        int redPacketPartNum = redPacket.getRedPacketPartNum();
        double[] partValues = RedPacketHelp.clipRedPacket(redPacketAmount, redPacketPartNum);
        List<RedPacketPart> parts = new ArrayList<>();
        for (int i=0;i<partValues.length;i++){
            RedPacketPart redPacketPart = createRedPacketPart(redPacket, partValues[i]);
            parts.add(redPacketPart);
        }
        return parts;
    }

    /**
     * 创建红包部分
     * @param redPacket
     * @param partValue
     * @return
     */
    private RedPacketPart createRedPacketPart(RedPacket redPacket, double partValue) {
        RedPacketPart redPacketPart = new RedPacketPart();
        redPacketPart.setRedPacketId(redPacket.getId());
        redPacketPart.setRedPacketPartAmount(partValue);
        return redPacketPart;
    }

    /**
     * 创建红包
     * @param userId
     * @param room_id
     * @param redPcketAmount
     * @param redPacketPartNum
     * @param redPacketType
     */
    private RedPacket createRedPacket(@RequestParam(value = "user_id") long userId, @RequestParam(value = "room_id") long room_id, @RequestParam(value = "red_packet_amount") double redPcketAmount, @RequestParam(value = "red_packet_part_num") int redPacketPartNum, @RequestParam(value = "red_packet_type") int redPacketType) {
        RedPacket redPacket = new RedPacket();
        redPacket.setUserId(userId);
        redPacket.setRoomId(room_id);
        redPacket.setRedPacketAmount(redPcketAmount);
        redPacket.setRedPacketPartNum(redPacketPartNum);
        redPacket.setRedPacketType(redPacketType);
        redPacket.setCreateTime(System.currentTimeMillis());
        return redPacket;
    }
    @RequestMapping(value = "/pickRedPacket")
    public void pickRedPacket(@RequestParam(value = "user_id") long userId,@RequestParam(value = "red_packet_id") long red_packet_id){
        User user = userRepository.findOne(userId);
        List<RedPacketPart> parts = redPacketPartRepository.findAllByRedPacketIdAndUserId(red_packet_id,0);
        if (parts==null||parts.size()<=0){
            //TODO 已经抢完了 查看红包详情
        }
        RedPacketPart redPacketPart = parts.get(0);
        redPacketPart.setUserId(userId);
        redPacketPartRepository.save(redPacketPart);
        double binding_gold = user.getBinding_gold();
        user.setBinding_gold(binding_gold+redPacketPart.getRedPacketPartAmount());
        userRepository.save(user);
        RedPacket redPacket = redPacketRepository.findOne(red_packet_id);

    }


}

