package com.example.repository;

import com.example.entity.RedPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/8.
 */
@Repository
public interface RedPacketRepository extends JpaRepository<RedPacket,Long>{

}
