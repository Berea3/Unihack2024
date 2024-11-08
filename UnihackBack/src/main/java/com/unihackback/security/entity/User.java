package com.unihackback.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unihackback.entity.Case;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private String id;

    private String email;
    private String password;

//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String>roles;
    private String roles;

    public User()
    {

    }

    public User(String id, String email, String password, String roles)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    //    public User(Long id, String username, String password, List<String> roles) //, List<String> roles
//    {
//        this.id=id;
//        this.username=username;
//        this.password=password;
//        this.roles=roles;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
//                ", roles=" + roles +
                '}';
    }

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    List<Case> cases;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Case> apps;

    public String getId() {return this.id;}
    public String getPassword() {return this.password;}
    public String getEmail() {return this.email;}
    public String getRoles() {return this.roles;}

    public void setId(String id) {this.id=id;}
    public void setPassword(String password) {this.password=password;}
    public void setEmail(String email) {this.email=email;}
    public void setRoles(String roles) {this.roles=roles;}
}
