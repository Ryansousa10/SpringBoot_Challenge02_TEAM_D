package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.dto.CreateOrderResponseDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParseToDTO {

    private final ModelMapper modelMapper;

    public CreateOrderResponseDTO toDTO(OrderModel model) {
        return modelMapper.map(model, CreateOrderResponseDTO.class);
    }
}
