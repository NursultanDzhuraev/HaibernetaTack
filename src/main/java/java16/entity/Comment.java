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
@Table(name = "comments")
public class Comment  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commit_gen")
    @SequenceGenerator(name = "commit_gen",sequenceName = "commit_seq", allocationSize = 1)
    private Long id;
    private String text;
    private LocalDate commentDate;

    public Comment(String text) {
        this.text = text;
    }

    @PrePersist @PreUpdate
    private void CreateDate() {
        this.commentDate = LocalDate.now();

    }
    @ToString.Exclude
    @ManyToMany(mappedBy = "comment")
    private List<User> user;
    @ToString.Exclude
    @ManyToOne
    private Post post;
}
