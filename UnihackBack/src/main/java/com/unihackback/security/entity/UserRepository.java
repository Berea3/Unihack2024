package com.unihackback.security.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value="select user from User user where user.email = ?1")
    User findByUsername(String username);

    @Query(value = "select user from User user where user.id=?1")
    Optional<User> findById(String id);

}
