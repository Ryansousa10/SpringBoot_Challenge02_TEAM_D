package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.proxy.OrdersProxy;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrdersProxy proxy;

    public void getAllFeedbacksService() {
    }

    public ResponseEntity<FeedbackModel> getFeedbackById(int id) {
        Optional<FeedbackModel> feedbackOptional = feedbackRepository.findById(id);

        if (feedbackOptional.isPresent()) {
            FeedbackModel feedback = feedbackOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(feedback);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public void createFeedbackService () {
        }

        public void updateFeedbackService () {
        }

        public void deleteFeedbackService () {
        }
    }

