package java16.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name = "post_gen",sequenceName = "post_seq", allocationSize = 1)
    private Long id;
    private String imageURl;
    @Column(length = 1000)
    private String description;
    private LocalDate createdAt;

    public Post(String imageURl, String description) {
        this.imageURl = imageURl;
        this.description = description;
    }

    @PrePersist @PreUpdate
    private void onCreate() {
        this.createdAt = LocalDate.now();
    }
    @ToString.Exclude
    @ManyToOne
    private User owner;
    @ToString.Exclude
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
