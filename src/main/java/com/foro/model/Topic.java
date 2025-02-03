package com.foro.model;

import com.foro.dto.TopicRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopic;
    private String title;
    @Lob
    private String message;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responses;


    public Topic(@NotBlank String title, @NotBlank String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;

    }

}
