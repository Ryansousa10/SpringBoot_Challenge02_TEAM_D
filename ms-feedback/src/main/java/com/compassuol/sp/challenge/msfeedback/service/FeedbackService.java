package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.controller.exception.ResponseErrorTemplate;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.ProductNotFoundException;
import com.compassuol.sp.challenge.msfeedback.dto.FeedbackRequestDTO;
import com.compassuol.sp.challenge.msfeedback.dto.OrderResponseDTO;
import com.compassuol.sp.challenge.msfeedback.proxy.OrdersProxy;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrdersProxy proxy;

    public void getAllFeedbacksService() {
    }

    public ResponseEntity<Object> getFeedbackById(int feedbackId) {
        FeedbackModel feedback = feedbackRepository.findById(feedbackId).orElse(null);

        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            String errorMessage = "Feedback com o ID " + feedbackId + " não encontrado.";
            ResponseErrorTemplate errorResponse = new ResponseErrorTemplate(HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.toString(),
                    errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }



    public FeedbackModel createFeedbackService(FeedbackRequestDTO request) {
        try {
            OrderResponseDTO feedbackResponse = proxy.getOrderById(request.getOrder_id());
            if (feedbackResponse.getStatus().equalsIgnoreCase("canceled"))
                throw new BusinessErrorException("cannot create feedbacks for canceled orders");
        } catch (FeignException ex) {
            throw new ProductNotFoundException("order_id not found in database");
        }
        return feedbackRepository.save(getFeedback(request));
    }
    public void createFeedbackService () {
        }

        public void updateFeedbackService () {
        }

        public void deleteFeedbackService () {
        }


    public FeedbackModel getFeedback(FeedbackRequestDTO request) {
        return new FeedbackModel(request.getScale(), request.getComment(),
                request.getOrder_id());
    }
}

