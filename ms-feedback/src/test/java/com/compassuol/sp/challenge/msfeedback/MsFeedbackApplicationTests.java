package com.compassuol.sp.challenge.msfeedback;

import com.compassuol.sp.challenge.msfeedback.controller.FeedbackController;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MsFeedbackApplicationTests {

	@Mock
	private FeedbackService feedbackService;

	@InjectMocks
	private FeedbackController feedbackController;

	@Test
	public void testGetFeedbackById() {
		int feedbackId = 1;
		FeedbackModel mockFeedback = new FeedbackModel(); // Crie um objeto FeedbackModel de teste
		Optional<FeedbackModel> optionalFeedback = Optional.of(mockFeedback);

		when(feedbackService.getFeedbackById(feedbackId)).thenReturn(optionalFeedback);

		ResponseEntity<FeedbackModel> responseEntity = feedbackController.getFeedbackById(feedbackId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(mockFeedback, responseEntity.getBody());
	}

	@Test
	public void testGetFeedbackByIdNotFound() {
		int feedbackId = 1;

		when(feedbackService.getFeedbackById(feedbackId)).thenReturn(Optional.empty());

		ResponseEntity<FeedbackModel> responseEntity = feedbackController.getFeedbackById(feedbackId);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
}

