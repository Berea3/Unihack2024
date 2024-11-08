package com.unihackback.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihackback.entity.generator.Generator;
import com.unihackback.security.entity.User;
import com.unihackback.security.entity.UserRepository;
import com.unihackback.security.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/security")
    public String security() throws JsonProcessingException
    {
        Map<String, Object> responseBody=new HashMap<>();
        responseBody.put("loggedin","yes");

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(responseBody);
        return json;
    }


    @PostMapping("/security/sign-up")
    public String securitySignUp(@RequestBody User user)
    {
        if (!UserService.userExists(user.getEmail(),userRepository))
        {
            user.setId(Generator.generateId());
            System.out.println(user.getId());
            String salt= BCrypt.gensalt();
            user.setPassword(BCrypt.hashpw(user.getPassword(),salt));
            userRepository.save(user);
            return "user added";
        }
        else
        {
            return "user already exists";
        }
    }
}