package java16.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @SequenceGenerator(name = "id_gen",sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @ToString.Exclude
    @OneToOne(mappedBy = "ownerUser", cascade = CascadeType.ALL)
    private Profile profile;
@ToString.Exclude
     @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Post> post;
    @ToString.Exclude
     @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> comment;
}
