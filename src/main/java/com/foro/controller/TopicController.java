package com.foro.controller;

import com.foro.dto.TopicRequestDTO;
import com.foro.dto.TopicResponseDTO;
import com.foro.model.Course;
import com.foro.model.Topic;
import com.foro.model.User;
import com.foro.repository.CourseRepository;
import com.foro.repository.TopicRepository;
import com.foro.repository.UserRepository;
import com.foro.sevice.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private final TopicRepository topicRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private TopicService topicService;


    public TopicController(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;

    }

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid TopicRequestDTO topicRequestDTO) {
        User author = userRepository.findById(topicRequestDTO.authorID())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Course course = courseRepository.findById(topicRequestDTO.courseId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topic topic = new Topic(
                topicRequestDTO.title(),
                topicRequestDTO.message(),
                author,
                course
        );

        topicRepository.save(topic);
    }

    @GetMapping
    public List<TopicResponseDTO> getAllTopics() {
        return topicService.getAllTopics();
    }


    @GetMapping("/{id}")
    public TopicResponseDTO getTopic(@PathVariable Long id) {
        return topicService.getTopicById(id);
    }
}
