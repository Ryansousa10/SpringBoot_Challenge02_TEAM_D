package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.FeedbackNotFoundException;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class FeedbackServiceTest {

    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;

    @BeforeEach
    void setUp() {
        feedbackRepository = Mockito.mock(FeedbackRepository.class);
        feedbackService = new FeedbackService(feedbackRepository);
    }

    @Test
    void testGetAllFeedbacksServiceWithException() {
        doThrow(new FeedbackNotFoundException("Feedbacks not found")).when(feedbackRepository).findAll();

        try {
            feedbackService.getAllFeedbacksService();
        } catch (FeedbackNotFoundException e) {
            assertEquals("Feedbacks not found", e.getMessage());
        }
    }

    @Test
    void testGetAllFeedbacksServiceWithSuccess() {
        List<FeedbackModel> feedbacks = new ArrayList<>();
        when(feedbackRepository.findAll()).thenReturn(feedbacks);

        List<FeedbackModel> result = feedbackService.getAllFeedbacksService();

        assertEquals(feedbacks, result);
    }
}

