package com.foro.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResponse;
    @Lob
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private  User author;
    private boolean isSolve;
}
