package com.example.repository;

import com.example.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/7.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
