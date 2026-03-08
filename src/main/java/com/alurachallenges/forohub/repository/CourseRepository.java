package com.alurachallenges.forohub.repository;

import com.alurachallenges.forohub.domain.curso.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
