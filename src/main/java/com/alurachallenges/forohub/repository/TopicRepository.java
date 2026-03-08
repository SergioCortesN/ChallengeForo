package com.alurachallenges.forohub.repository;

import com.alurachallenges.forohub.domain.topico.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleAndMessage(String title, String message);

}
