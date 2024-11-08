package com.unihackback.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tickets.security.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(config -> config
                .requestMatchers("/security/unauthenticated").permitAll()   // this works without logging in, for example for sign up
                .requestMatchers("/theatres/create").hasAuthority("organizer")
                .anyRequest().authenticated());
        httpSecurity.formLogin(form -> form.usernameParameter("username").passwordParameter("password").loginPage("/login")
                .permitAll().successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException { // Authentication success handler decides what to return if the login is successfull
//                System.out.println(authentication.toString());
                String json=authentication.getName();
                response.getWriter().write(json);
//                response.setStatus(200);
//                String json=mapper.writeValueAsString(responseBody);
//                response.setStatus(HttpServletResponse.SC_FOUND);
                response.setStatus(202);
            }
        }).failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                        response.setStatus(HttpServletResponse.SC_ACCEPTED);

//                        response.setContentType("application/json");
//                        Map<String, Boolean> responseBody=new HashMap<>();
//                        responseBody.put("loggedin",false);
//
//                        ObjectMapper mapper=new ObjectMapper();
//                        String json=mapper.writeValueAsString(responseBody);
//                        response.getWriter().write(json);
//                        response.getWriter().write("proba");
                        User user=new User();
                        ObjectMapper mapper=new ObjectMapper();
                        String json=mapper.writeValueAsString(user);
                        response.setStatus(202);
                        response.getWriter().write(json);
                    }
                }));

        httpSecurity.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);

                response.setContentType("application/json");
                Map<String, Boolean> responseBody=new HashMap<>();
                responseBody.put("loggedin",false);

                ObjectMapper mapper=new ObjectMapper();
                String json=mapper.writeValueAsString(responseBody);
                response.getWriter().write(json);
            }
        }));

//        httpSecurity.rememberMe(withDefaults());

        httpSecurity.logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(false).logoutUrl("/logout").permitAll());
        httpSecurity.httpBasic(withDefaults());

        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.rememberMe(rememberMe -> rememberMe.rememberMeParameter("rememberMe").userDetailsService(myUserDetailsService).alwaysRemember(true));

//        httpSecurity.rememberMe(rememberMeConfigurer -> rememberMeConfigurer)

        return httpSecurity.build();
    }

    @Autowired
    private MyAuthenticationProvider authenticationProvider;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration=new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");

        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
