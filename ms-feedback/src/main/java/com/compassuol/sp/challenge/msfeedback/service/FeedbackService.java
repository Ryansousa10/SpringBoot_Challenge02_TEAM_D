package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.controller.exception.ResponseErrorTemplate;
import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.FeedbackNotFoundException;
import com.compassuol.sp.challenge.msfeedback.dto.FeedbackRequestDTO;
import com.compassuol.sp.challenge.msfeedback.dto.FeedbackResponseDTO;
import com.compassuol.sp.challenge.msfeedback.dto.OrderResponseDTO;
import com.compassuol.sp.challenge.msfeedback.dto.ScaleEnum;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.proxy.OrdersProxy;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrdersProxy proxy;

    public List<FeedbackModel> getAllFeedbacksService() {
        try {
            return feedbackRepository.findAll();
        } catch (Exception e) {
            throw new FeedbackNotFoundException("Feedbacks not found", e);
        }
    }

    public ResponseEntity<Object> getFeedbackById(long feedbackId) {
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
            throw new FeedbackNotFoundException("order_id not found in database");
        }
        return feedbackRepository.save(getFeedback(request));
    }

    public void updateFeedbackService() {}

    public FeedbackResponseDTO deleteFeedbackService(Long id) {
        FeedbackModel feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback não encontrado"));

        FeedbackResponseDTO responseDTO = mapToResponseDTO(feedback);
        feedbackRepository.deleteById(id);

        return responseDTO;
    }

    FeedbackResponseDTO mapToResponseDTO(FeedbackModel feedbackModel) {
        FeedbackResponseDTO responseDTO = new FeedbackResponseDTO();
        responseDTO.setId(feedbackModel.getId());
        responseDTO.setScale(ScaleEnum.valueOf(feedbackModel.getScale().toString()));
        responseDTO.setComment(feedbackModel.getComment());
        responseDTO.setOrder_id(feedbackModel.getOrder_id());
        return responseDTO;
    }

    public FeedbackModel getFeedback(FeedbackRequestDTO request) {
        return new FeedbackModel(request.getScale(), request.getComment(),
                request.getOrder_id());
    }
}
