package com.unihackback.controller;

import com.unihackback.security.entity.User;
import com.unihackback.security.entity.UserRepository;
import com.unihackback.security.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get-all-doctors")
    public List<User> getAllDoctors() {
        return userRepository.findAllDoctors();
    }

    @PostMapping("/add-doctor")
    public void addDoctor(User user) {
        if (!UserService.userExists(user.getEmail(),userRepository))
        {
            user.setRoles("DOCTOR");
            userRepository.save(user);
        }
    }
}
