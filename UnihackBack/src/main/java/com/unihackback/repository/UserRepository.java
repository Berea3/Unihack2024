package com.unihackback.repository;

import com.unihackback.security.entity.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.roles = 'DOCTOR'")
    List<User> findAllDoctors();

}
