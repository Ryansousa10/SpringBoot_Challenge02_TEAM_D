package com.compassuol.sp.challenge.msfeedback.controller;

import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public void getAllFeedbacks() {}

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackModel> getFeedbackById(@PathVariable int id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping
    public void createFeedback() {}

    @PutMapping("/{id}")
    public void updateFeedback() {}

    @DeleteMapping("/{id}")
    public void deleteFeedback() {}
}
