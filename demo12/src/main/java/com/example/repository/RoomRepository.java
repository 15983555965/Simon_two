package com.example.repository;

import com.example.entity.Room;
import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/7.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> queryFirst10ByRoomId(String roomId, Pageable pageable);
}
