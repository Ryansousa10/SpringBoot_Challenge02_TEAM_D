package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.FeedbackNotFoundException;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public List<FeedbackModel> getAllFeedbacksService() {
        try {
            return feedbackRepository.findAll();
        } catch (Exception e) {
            throw new FeedbackNotFoundException("Feedbacks not found", e);
        }
    }
}
