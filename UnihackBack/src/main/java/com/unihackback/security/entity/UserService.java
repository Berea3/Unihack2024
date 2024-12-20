package com.unihackback.security.entity;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User validateUser(String username, String password, UserRepository userRepository)
    {
        User user=userRepository.findByUsername(username);
        if (user!=null)
        {
            if (BCrypt.checkpw(password,user.getPassword())) return user;
            else return null;
        }
        else return null;
    }

    public static boolean userExists(String email, UserRepository userRepository)
    {
        User user=userRepository.findByUsername(email);
        if (user!=null) return true;
        else return false;
    }

    public static String hashPassword(String password)
    {
        final long base=29;
        final long mod=385934743;
        long pow=1;
        long hash=0;

        for (int i=0;i<password.length();i++)
        {
            hash=(hash*base+password.charAt(i))%mod;
            pow=(pow*base)%mod;
        }
        return String.valueOf(hash);
    }
}
