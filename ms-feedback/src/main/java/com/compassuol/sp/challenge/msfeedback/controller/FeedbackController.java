package com.compassuol.sp.challenge.msfeedback.controller;

import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.dto.FeedbackRequestDTO;
import com.compassuol.sp.challenge.msfeedback.dto.FeedbackResponseDTO;
import com.compassuol.sp.challenge.msfeedback.service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackModel> getAllFeedbacks() {
        return feedbackService.getAllFeedbacksService();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFeedbackById(@PathVariable("id") int feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }

    @PostMapping
    public ResponseEntity<Object> createFeedback(@RequestBody @Valid FeedbackRequestDTO request) {
        FeedbackResponseDTO feedback = ParseModelToDTO(feedbackService.createFeedbackService(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }

    @PutMapping("/{id}")
    public void updateFeedback() {}

    @DeleteMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> deleteFeedback(@PathVariable Long id) {
        FeedbackResponseDTO responseDTO = feedbackService.deleteFeedbackService(id);
        return ResponseEntity.ok(responseDTO);
    }

    private FeedbackResponseDTO ParseModelToDTO(FeedbackModel feedbackModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(feedbackModel, FeedbackResponseDTO.class);
    }
}
