package com.foro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String username;
    private String password;
    private String firstName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_profiles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private List<Profile> profiles;
}
