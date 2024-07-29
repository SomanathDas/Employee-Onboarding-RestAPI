package com.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.onboarding.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Employee employee;

    public User(String username, String password, Role roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
