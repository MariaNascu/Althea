package com.fortech.repository;

import com.fortech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int id);

    User findByUsername(String username);

    long countByEmail(String email);

    void findByEmail(String email);

    void findByPhone(String phone);

    List<User> findAll();
}
