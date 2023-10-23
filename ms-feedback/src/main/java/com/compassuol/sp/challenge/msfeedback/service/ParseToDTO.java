package com.compassuol.sp.challenge.msfeedback.service;

import com.compassuol.sp.challenge.msfeedback.dto.FeedbackResponseDTO;
import com.compassuol.sp.challenge.msfeedback.model.FeedbackModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseToDTO {

    private final ModelMapper modelMapper;

    public FeedbackResponseDTO toDTO(FeedbackModel model) {
        return modelMapper.map(model, FeedbackResponseDTO.class);
    }
}
