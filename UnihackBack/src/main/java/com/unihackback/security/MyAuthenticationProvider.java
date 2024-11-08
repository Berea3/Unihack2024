package com.unihackback.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tickets.security.entities.User;
import com.tickets.security.entities.UserRepository;
import com.tickets.security.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private UserService userService=new UserService();

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException
//    {
//        String username=authentication.getName();
//        String password=authentication.getCredentials().toString();
////        System.out.println(userRepository.count());
//        User user=userService.validateUser(username,password,userRepository);
//
////        Map<String, Object> responseBody=new HashMap<>();
////        responseBody.put("username",username);
////        ObjectMapper mapper=new ObjectMapper();
////        String json=mapper.writeValueAsString(user);
//        if (user!=null) //return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
//        {
////            Map<String, Object> responseBody=new HashMap<>();
////            responseBody.put("username",username);
////            ArrayList<String> roles=new ArrayList<>();
////            roles= (ArrayList<String>) user.getRoles();
////            System.out.println(/user.getRoles().toString());
//            List<String> roles = user.getRoles();
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//            for (String role : roles) {
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
//                grantedAuthorities.add(grantedAuthority);
//            }
//
////            return grantedAuthorities;
//            ObjectMapper mapper=new ObjectMapper();
//            try {
//                String json=mapper.writeValueAsString(user);
//                return new UsernamePasswordAuthenticationToken(json,password,new ArrayList<>(grantedAuthorities));  //new ArrayList<>() grantedAuthorities
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
////            return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
//        }
////        if (user!=null) return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
//        else throw new BadCredentialsException("Bad credentials");
//    }

    @Override
    public Authentication authenticate(Authentication authentication)
    {
        String email=authentication.getName();
        String password=authentication.getCredentials().toString();
//        password=UserService.hashPassword(password);
        User user=userService.validateUser(email,password,userRepository);
        if (user!=null)       //return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
        {
            String[] roles = user.getRoles().split("_");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (String role : roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(grantedAuthority);
            }
            ObjectMapper mapper=new ObjectMapper();
            try {
                String json=mapper.writeValueAsString(user);
                return new UsernamePasswordAuthenticationToken(json,password,new ArrayList<>(grantedAuthorities));  //new ArrayList<>() grantedAuthorities
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        else throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
