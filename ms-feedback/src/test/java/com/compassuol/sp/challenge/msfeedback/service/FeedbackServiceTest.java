package com.compassuol.sp.challenge.msfeedback.service;

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

    @InjectMocks
    FeedbackService feedbackService;

    @Mock
    FeedbackRepository feedbackRepository;

    @Mock
    OrdersProxy proxy;

    @BeforeEach
    void setUp() {
        feedbackRepository = Mockito.mock(FeedbackRepository.class);
        feedbackService = new FeedbackService(feedbackRepository, proxy);
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
    public void CreateFeedback_withValidData_returnsFeedback() {
        var feedbackRequestDTO = new FeedbackRequestDTO();
        feedbackRequestDTO.setComment("test");
        feedbackRequestDTO.setOrder_id(1L);
        feedbackRequestDTO.setScale(ScaleEnum.SATISFIED);

        var orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setStatus("CONFIRMED");

        var feedbackModel = new FeedbackModel();
        feedbackModel.setScale(ScaleEnum.SATISFIED);
        feedbackModel.setOrder_id(1L);
        feedbackModel.setComment("comment");
        feedbackModel.setId(12L);



        var feedbackModelCaptor = ArgumentCaptor.forClass(FeedbackModel.class);

        when(proxy.getOrderById(anyLong())).thenReturn(orderResponseDTO);
        when(feedbackRepository.save(feedbackModelCaptor.capture()))
                .thenReturn(feedbackModel);

        FeedbackModel feedbackResponse = feedbackService.createFeedbackService(feedbackRequestDTO);
        assertNotNull(feedbackResponse);
    }

    @Test
    public void CreateFeedback_withInvalidId_returnsException() {
        var feedbackRequestDTO = new FeedbackRequestDTO();
        feedbackRequestDTO.setComment("test");
        feedbackRequestDTO.setOrder_id(1L);
        feedbackRequestDTO.setScale(ScaleEnum.SATISFIED);

        when(proxy.getOrderById(1L)).thenThrow(FeedbackNotFoundException.class);

        assertThatThrownBy(() -> feedbackService.createFeedbackService(feedbackRequestDTO))
                .isInstanceOf(FeedbackNotFoundException.class);
    }

    @Test
    public void CreateFeedback_withInvalidStatus_returnsException() {
        var feedbackRequestDTO = new FeedbackRequestDTO();
        feedbackRequestDTO.setComment("test");
        feedbackRequestDTO.setOrder_id(1L);
        feedbackRequestDTO.setScale(ScaleEnum.SATISFIED);

        var orderResponseDTO = new OrderResponseDTO();
        when(proxy.getOrderById(anyLong())).thenReturn(orderResponseDTO);
        orderResponseDTO.setStatus("CANCELED");

        assertThatThrownBy(() -> feedbackService.createFeedbackService(feedbackRequestDTO))
                .isInstanceOf(BusinessErrorException.class);
    }

    @Test
    void testDeleteFeedbackService() {
        // Configurar feedback simulado para exclusão
        FeedbackModel simulatedFeedback = new FeedbackModel();
        simulatedFeedback.setId(1L);
        simulatedFeedback.setScale(ScaleEnum.SATISFIED);
        simulatedFeedback.setComment("Comment here");
        simulatedFeedback.setOrder_id(1L);

        // Simular a busca pelo feedback a ser excluído
        when(feedbackRepository.findById(any())).thenReturn(Optional.of(simulatedFeedback));

        // Verificar a chamada do método com um argumento correspondente
        FeedbackResponseDTO responseDTO = feedbackService.deleteFeedbackService(1L);
        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getScale()).isEqualTo(ScaleEnum.SATISFIED);
        assertThat(responseDTO.getComment()).isEqualTo("Comment here");
        assertThat(responseDTO.getOrder_id()).isEqualTo(1L);
    }

    @Test
    void testDeleteFeedbackServiceWithFeedbackNotFound() {
        // Simular a busca pelo feedback que não foi encontrado (retorno vazio)
        when(feedbackRepository.findById(any())).thenReturn(Optional.empty());

        // Agora, chame o método que você está testando e verifique o comportamento esperado
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
}
