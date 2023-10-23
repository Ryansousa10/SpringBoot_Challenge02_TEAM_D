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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

    public static FeedbackRequestDTO feedbackRequestDTO;
    public static OrderResponseDTO orderResponseDTO;
    public static FeedbackModel feedbackModel;
    public static FeedbackResponseDTO feedbackResponseDTO;

    @InjectMocks
    FeedbackService feedbackService;

    @Mock
    FeedbackRepository feedbackRepository;

    @Mock
    OrdersProxy proxy;

    @Mock
    ParseToDTO parseToDTO;

    @BeforeEach
    public void setUp() {
        feedbackRequestDTO = new FeedbackRequestDTO();
        feedbackRequestDTO.setComment("test");
        feedbackRequestDTO.setOrder_id(1L);
        feedbackRequestDTO.setScale(ScaleEnum.SATISFIED);

        orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setStatus("CONFIRMED");

        feedbackModel = new FeedbackModel();
        feedbackModel.setScale(ScaleEnum.SATISFIED);
        feedbackModel.setOrder_id(1L);
        feedbackModel.setComment("comment");
        feedbackModel.setId(12L);

        feedbackResponseDTO = new FeedbackResponseDTO();
        feedbackResponseDTO.setId(1L);
        feedbackResponseDTO.setOrder_id(1L);
        feedbackResponseDTO.setComment("very good my boy");
        feedbackResponseDTO.setScale(ScaleEnum.SATISFIED);
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

    @Test
    public void CreateFeedback_withValidData_returnsFeedbackDTO() {
        var feedbackModelCaptor = ArgumentCaptor.forClass(FeedbackModel.class);

        when(parseToDTO.toDTO(feedbackModelCaptor.capture())).thenReturn(feedbackResponseDTO);
        when(proxy.getOrderById(anyLong())).thenReturn(orderResponseDTO);
        when(feedbackRepository.save(feedbackModelCaptor.capture()))
                .thenReturn(feedbackModel);

        FeedbackResponseDTO feedbackResponse = feedbackService.createFeedbackService(feedbackRequestDTO);
        assertNotNull(feedbackResponse);
    }

    @Test
    public void CreateFeedback_withInvalidId_returnsException() {
        when(proxy.getOrderById(1L)).thenThrow(FeedbackNotFoundException.class);

        assertThatThrownBy(() -> feedbackService.createFeedbackService(feedbackRequestDTO))
                .isInstanceOf(FeedbackNotFoundException.class);
    }

    @Test
    public void CreateFeedback_withInvalidStatus_returnsException() {
        when(proxy.getOrderById(anyLong())).thenReturn(orderResponseDTO);
        orderResponseDTO.setStatus("CANCELED");

        assertThatThrownBy(() -> feedbackService.createFeedbackService(feedbackRequestDTO))
                .isInstanceOf(BusinessErrorException.class);
    }

    @Test
    void testDeleteFeedbackService() {
        FeedbackModel simulatedFeedback = new FeedbackModel();
        simulatedFeedback.setId(1L);
        simulatedFeedback.setScale(ScaleEnum.SATISFIED);
        simulatedFeedback.setComment("Comment here");
        simulatedFeedback.setOrder_id(1L);

        when(feedbackRepository.findById(any())).thenReturn(Optional.of(simulatedFeedback));

        FeedbackResponseDTO responseDTO = feedbackService.deleteFeedbackService(1L);
        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getScale()).isEqualTo(ScaleEnum.SATISFIED);
        assertThat(responseDTO.getComment()).isEqualTo("Comment here");
        assertThat(responseDTO.getOrder_id()).isEqualTo(1L);
    }

    @Test
    void testDeleteFeedbackServiceWithFeedbackNotFound() {
        when(feedbackRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> feedbackService.deleteFeedbackService(1L))
                .isInstanceOf(FeedbackNotFoundException.class);
    }

    @Test
    void testMapToResponseDTO() {
        FeedbackModel simulatedFeedback = new FeedbackModel();
        simulatedFeedback.setId(1L);
        simulatedFeedback.setScale(ScaleEnum.SATISFIED);
        simulatedFeedback.setComment("Comment here");
        simulatedFeedback.setOrder_id(1L);

        FeedbackResponseDTO responseDTO = feedbackService.mapToResponseDTO(simulatedFeedback);

        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getScale()).isEqualTo(ScaleEnum.SATISFIED);
        assertThat(responseDTO.getComment()).isEqualTo("Comment here");
        assertThat(responseDTO.getOrder_id()).isEqualTo(1L);
    }

    @Test
    void testGetFeedbackByIdFound() {
        long feedbackId = 1;
        FeedbackModel feedbackModel = new FeedbackModel();
        feedbackModel.setId(feedbackId);
        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(feedbackModel));

        ResponseEntity<Object> response = feedbackService.getFeedbackById(feedbackId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(feedbackModel, response.getBody());
    }
    @Test
    void testGetFeedbackByIdNotFound() {
        long feedbackId = 1;
        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = feedbackService.getFeedbackById(feedbackId);

        assertEquals(404, response.getStatusCodeValue());
        ResponseErrorTemplate errorResponse = (ResponseErrorTemplate) response.getBody();
        assertEquals("Feedback com o ID 1 não encontrado.", errorResponse.getMessage());
    }

    @Test
    public void updateFeedbacks_withValidData_ReturnsFeedback() {
        var feedbackModelCaptor = ArgumentCaptor.forClass(FeedbackModel.class);
        when(parseToDTO.toDTO(feedbackModelCaptor.capture())).thenReturn(feedbackResponseDTO);
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.of(feedbackModel));
        when(proxy.getOrderById(anyLong())).thenReturn(orderResponseDTO);
        when(feedbackRepository.save(feedbackModelCaptor.capture())).thenReturn(feedbackModel);

        FeedbackResponseDTO response = feedbackService.updateFeedbackService(1L, feedbackRequestDTO);
        assertNotNull(response);
    }

    @Test
    public void updateFeedbacks_withInValidId_ReturnsException() {
        when(feedbackRepository.findById(1L)).thenThrow(FeedbackNotFoundException.class);
        assertThatThrownBy(() -> feedbackService.updateFeedbackService(1L, feedbackRequestDTO))
                .isInstanceOf(FeedbackNotFoundException.class);
    }

    @Test
    public void updateFeedbacks_withInValidOrderId_ReturnsException() {
        FeedbackRequestDTO request = feedbackRequestDTO;
        request.setOrder_id(3L);
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.of(feedbackModel));
        when(proxy.getOrderById(3L)).thenThrow(FeedbackNotFoundException.class);

        assertThatThrownBy(() -> feedbackService.updateFeedbackService(1L, request))
                .isInstanceOf(FeedbackNotFoundException.class);
    }
}
