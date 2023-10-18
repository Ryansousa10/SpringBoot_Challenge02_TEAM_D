package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.proxy.OrdersProxy;
import com.compassuol.sp.challenge.msfeedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrdersProxy proxy;

    public void getAllFeedbacksService() {}

    public void getFeedbacksByIdService() {}

    public void createFeedbackService() {}

    public void updateFeedbackService() {}

    public void deleteFeedbackService() {}
}
