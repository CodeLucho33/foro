package com.foro.repository;

import com.foro.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepositoy extends JpaRepository<Course, Long> {
}
