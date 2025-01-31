package java16.entity;

import jakarta.persistence.*;
import java16.enam.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_gen")
    @SequenceGenerator(name = "profile_gen",sequenceName = "profile_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String biography;

    public Profile(String firstName, LocalDate birthday, Gender gender, String biography) {
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.biography = biography;
    }
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private User ownerUser;

}
