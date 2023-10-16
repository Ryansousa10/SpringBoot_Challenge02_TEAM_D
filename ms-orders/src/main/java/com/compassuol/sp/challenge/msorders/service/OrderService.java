package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderModel> getAllOrdersService() {
        //para implementer
        return orderRepository.findAll();
    }

    public void getOrderByIdService() {
        //para implementer
    }

    public void createOrderService() {
        //para implementer
        //lembrar de usar o feign pra buscar os products
    }

    public void updateOrderService() {
        //para implementer
    }

    public void cancelOrderByIdService() {
        //para implementer
    }
}
