package com.foro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfile;
    private String name;
}
