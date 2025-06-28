package com.Smart_Health.Smart_Health_Backend.Feedback_microService.Repostery;

import com.Smart_Health.Smart_Health_Backend.Feedback_microService.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // You can add custom query methods here if needed
}
