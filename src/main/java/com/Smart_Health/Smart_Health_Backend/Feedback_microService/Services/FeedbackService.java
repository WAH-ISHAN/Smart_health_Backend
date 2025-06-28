package com.Smart_Health.Smart_Health_Backend.Feedback_microService.Services;
import com.Smart_Health.Smart_Health_Backend.Feedback_microService.Entity.Feedback;
import com.Smart_Health.Smart_Health_Backend.Feedback_microService.Repostery.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
