package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/2/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
