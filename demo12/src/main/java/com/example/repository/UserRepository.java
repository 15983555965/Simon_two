package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findOneByAccountAndPassword(String account, String password);

    List<User> findAllByRoomId(long roomId);

    User findAllByToken(String token);
}
