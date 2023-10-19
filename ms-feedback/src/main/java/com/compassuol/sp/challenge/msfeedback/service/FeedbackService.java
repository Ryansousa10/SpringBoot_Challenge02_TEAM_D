package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes.FeedbackNotFoundException;
import com.compassuol.sp.challenge.msfeedback.dto.FeedbackRequestDTO;
import com.compassuol.sp.challenge.msfeedback.dto.OrderResponseDTO;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import com.compassuol.sp.challenge.msfeedback.proxy.OrdersProxy;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrdersProxy proxy;

    public void getFeedbacksByIdService() {}

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

    public List<FeedbackModel> getAllFeedbacksService() {
        try {
            return feedbackRepository.findAll();
        } catch (Exception e) {
            throw new FeedbackNotFoundException("Feedbacks not found", e);
        }
    }
    public void updateFeedbackService() {}

    public void deleteFeedbackService() {}

    public FeedbackModel getFeedback(FeedbackRequestDTO request) {
        return new FeedbackModel(request.getScale(), request.getComment(),
                request.getOrder_id());
    }
}
