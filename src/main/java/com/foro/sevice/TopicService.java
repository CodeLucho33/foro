package com.foro.sevice;

import com.foro.dto.TopicResponseDTO;
import com.foro.dto.TopicUpdateDTO;
import com.foro.model.Course;
import com.foro.model.Topic;
import com.foro.model.User;
import com.foro.repository.CourseRepository;
import com.foro.repository.TopicRepository;
import com.foro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

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

    public TopicResponseDTO getTopicById(Long id) {
        Topic topic = topicRepository.findByIdWithAuthorAndCourse(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con ID: " + id));
        return mapToTopicResponseDTO(topic);
    }

    @Transactional
    public TopicResponseDTO updateTopic(Long id, TopicUpdateDTO topicUpdateDTO) {
        // Busca el tópico existente
        Topic topic = topicRepository.findByIdWithAuthorAndCourse(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con ID: " + id));

        // Actualiza los campos
        topic.setTitle(topicUpdateDTO.title());
        topic.setMessage(topicUpdateDTO.message());
        topic.setStatus(topicUpdateDTO.status());

        // Actualiza el autor (si es necesario)
        if (topicUpdateDTO.authorId() != null) {
            User author = userRepository.findById(topicUpdateDTO.authorId())
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + topicUpdateDTO.authorId()));
            topic.setAuthor(author);
        }

        // Actualiza el curso (si es necesario)
        if (topicUpdateDTO.courseId() != null) {
            Course course = courseRepository.findById(topicUpdateDTO.courseId())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + topicUpdateDTO.courseId()));
            topic.setCourse(course);
        }

        // Guarda los cambios
        topicRepository.save(topic);

        // Retorna el tópico actualizado como DTO
        return mapToTopicResponseDTO(topic);
    }
    @Transactional
    public void deleteTopic(Long id) {
        // Verifica si el tópico existe
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con ID: " + id));

        // Elimina el tópico
        topicRepository.delete(topic);
    }
}