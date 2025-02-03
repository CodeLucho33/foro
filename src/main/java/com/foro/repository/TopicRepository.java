package com.foro.repository;

import com.foro.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndMessage(String tittle, String message);

    @Query("SELECT t FROM Topic t JOIN FETCH t.author JOIN FETCH t.course")
    List<Topic> findAllWithAuthorAndCourse();
}
