package com.foro.sevice;

import com.foro.dto.TopicResponseDTO;
import com.foro.model.Topic;
import com.foro.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<TopicResponseDTO> getAllTopics() {
        List<Topic> topics = topicRepository.findAllWithAuthorAndCourse();
        return topics.stream()
                .map(this::mapToTopicResponseDTO)
                .collect(Collectors.toList());
    }

    private TopicResponseDTO mapToTopicResponseDTO(Topic topic) {
        return new TopicResponseDTO(
                topic.getIdTopic(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getDateCreated(),
                topic.isStatus(),
                topic.getAuthor().getFirstName(),  // Nombre del autor
                topic.getCourse().getName()       // Nombre del curso
        );
    }
}