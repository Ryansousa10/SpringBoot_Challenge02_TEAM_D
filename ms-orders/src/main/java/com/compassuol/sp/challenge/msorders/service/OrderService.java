package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;



    public void getAllOrdersService() {
        //para implementer
    }
    public Optional<OrderModel> findby(Long id) {
        return orderRepository.findById(id);
    }

    public void createOrderService() {
        //para implementer
    }

    public void updateOrderService() {
        //para implementer
    }

    public void cancelOrderByIdService() {
        //para implementer
    }
}
