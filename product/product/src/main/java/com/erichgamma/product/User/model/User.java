package com.erichgamma.product.User.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String job;
    private String token;

    public User(Long id, String addressId, String username, String password, String name, String email, String phone, String token, String job) {
        this.id = id;
        this.addressId = addressId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.token = token;
        this.job = job;
    }
}
