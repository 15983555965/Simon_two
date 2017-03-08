package com.example.repository;

import com.example.entity.RedPacket;
import com.example.entity.RedPacketPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
@Repository
public interface RedPacketPartRepository extends JpaRepository<RedPacketPart,Long>{

    List<RedPacketPart> findAllByRedPacketIdAndUserId(long redPacketId,long userId);
}
