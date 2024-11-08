package com.unihackback.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unihackback.entity.Case;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
public class User {

    @Setter
    @Getter
    @Id
    @Column(name="id")
    private String id;

    @Getter
    @Setter
    private String email;
    @Setter
    @Getter
    private String password;

    @Getter
    @Setter
    private String roles;

    public User()
    {}

    public User(String id, String email, String password, String roles)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Case> cases;

}
