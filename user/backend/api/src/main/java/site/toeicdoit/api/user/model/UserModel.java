package site.toeicdoit.api.user.model;

import site.toeicdoit.api.article.model.Article;
import site.toeicdoit.api.common.BaseEntity;
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
public class UserModel extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
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
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Article> articles;
    private Double height;
    private Double weight;

}

