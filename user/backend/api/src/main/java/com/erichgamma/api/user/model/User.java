package com.erichgamma.api.user.model;

import com.erichgamma.api.article.model.Article;
import com.erichgamma.api.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "users")
@ToString(exclude = "id")
public class User extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long addressId;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String job;
    private String token;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Article> articles;
    private Double height;
    private Double weight;
}

