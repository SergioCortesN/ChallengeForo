package com.alurachallenges.forohub.controller;

import com.alurachallenges.forohub.domain.curso.Course;
import com.alurachallenges.forohub.domain.topico.Topic;
import com.alurachallenges.forohub.domain.topico.TopicRegisterDTO;
import com.alurachallenges.forohub.domain.topico.TopicResponseDTO;
import com.alurachallenges.forohub.domain.topico.TopicUpdateDTO;
import com.alurachallenges.forohub.domain.usuario.User;
import com.alurachallenges.forohub.repository.CourseRepository;
import com.alurachallenges.forohub.repository.TopicRepository;
import com.alurachallenges.forohub.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topics")
@Tag(name = "Topics", description = "Manage forum topics")
public class TopicController {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicController(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

        @PostMapping
        @Operation(summary = "Create a new topic")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Topic created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid or duplicate data")
        })
        public ResponseEntity<?> register(@RequestBody @Valid TopicRegisterDTO datos) {

        if (topicRepository.existsByTitleAndMessage(datos.title(), datos.message())) {
            return ResponseEntity.badRequest().body("Duplicate topic.");
        }

        User author = userRepository.findById(datos.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Course course = courseRepository.findById(datos.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Topic topic = new Topic(
            null,
            datos.title(),
            datos.message(),
            LocalDateTime.now(),
            "OPEN",
            author,
            course
        );

        topicRepository.save(topic);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "List all topics")
    public ResponseEntity<List<TopicResponseDTO>> list() {

        var lista = topicRepository.findAll()
                .stream()
                .map(t -> new TopicResponseDTO(
                        t.getId(),
                        t.getTitle(),
                        t.getMessage(),
                        t.getCreatedAt(),
                        t.getStatus(),
                        t.getAuthor().getName(),
                        t.getCourse().getName()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get topic details by id")
    public ResponseEntity<?> detail(@PathVariable Long id) {

        var optional = topicRepository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var t = optional.get();

        var response = new TopicResponseDTO(
            t.getId(),
            t.getTitle(),
            t.getMessage(),
            t.getCreatedAt(),
            t.getStatus(),
            t.getAuthor().getName(),
            t.getCourse().getName()
        );

        return ResponseEntity.ok(response);
    }

        @PutMapping("/{id}")
        @Operation(summary = "Update an existing topic")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Topic updated"),
            @ApiResponse(responseCode = "404", description = "Topic not found")
        })
        public ResponseEntity<?> update(@PathVariable Long id,
                        @RequestBody @Valid TopicUpdateDTO datos) {

        var optional = topicRepository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (topicRepository.existsByTitleAndMessage(datos.title(), datos.message())) {
            return ResponseEntity.badRequest().body("Duplicate topic.");
        }

        var topic = optional.get();

        topic.setTitle(datos.title());
        topic.setMessage(datos.message());

        topicRepository.save(topic);

        return ResponseEntity.ok().build();
    }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete a topic by id")
        @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Topic not found")
        })
        public ResponseEntity<?> delete(@PathVariable Long id) {

        var optional = topicRepository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
