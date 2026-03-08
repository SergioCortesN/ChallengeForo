package com.alurachallenges.forohub.domain.topico;

import com.alurachallenges.forohub.domain.curso.Course;
import com.alurachallenges.forohub.domain.usuario.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "topico",
                uniqueConstraints = {
                                @UniqueConstraint(columnNames = {"titulo", "mensaje"})
                })
public class Topic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(name = "titulo", nullable = false)
        private String title;

        @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
        private String message;

        @Column(name = "fecha_creacion", nullable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private String status;

        @ManyToOne
        @JoinColumn(name = "autor_id", nullable = false)
        private User author;

        @ManyToOne
        @JoinColumn(name = "curso_id", nullable = false)
        private Course course;

        public Topic() {
        }

        public Topic(Long id, String title, String message, LocalDateTime createdAt, String status, User author, Course course) {
                this.id = id;
                this.title = title;
                this.message = message;
                this.createdAt = createdAt;
                this.status = status;
                this.author = author;
                this.course = course;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public User getAuthor() {
                return author;
        }

        public void setAuthor(User author) {
                this.author = author;
        }

        public Course getCourse() {
                return course;
        }

        public void setCourse(Course course) {
                this.course = course;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Topic topic = (Topic) o;
                return Objects.equals(id, topic.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
