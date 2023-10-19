package com.compassuol.sp.challenge.msfeedback.controller;

import com.compassuol.sp.challenge.msfeedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public void getAllFeedbacks() {}

    @GetMapping("/{id}")
    public void getFeedbackById() {}

    @PostMapping
    public void createFeedback() {}

    @PutMapping("/{id}")
    public void updateFeedback() {}

    @DeleteMapping("/{id}")
    public void deleteFeedback() {}
}
