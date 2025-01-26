package com.foro.model;

import jakarta.persistence.*;
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
    private String tittle;
    @Lob
    private String message;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private boolean status;

   @ManyToOne
   private User author;
   @ManyToOne
   private Course course;

   @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Response> responses;

}
