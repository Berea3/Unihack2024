package com.unihackback.service;

import com.unihackback.repository.UserRepository;
import com.unihackback.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllDoctors() {
        return userRepository.findAllDoctors();
    }
}
