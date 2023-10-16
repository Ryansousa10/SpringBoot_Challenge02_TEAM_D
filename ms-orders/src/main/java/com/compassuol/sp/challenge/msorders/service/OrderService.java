package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

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

    public OrderModel cancelOrderByIdService(Long id, CancelOrderRequestDTO cancelOrderRequest) {
        Optional<OrderModel> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            OrderModel order = optionalOrder.get();

            if (order.getStatus() == StatusOrderEnum.SENT) {
                throw new OrderCancellationNotAllowedException("O pedido não pode ser cancelado, pois já foi enviado.");
            }

            LocalDate currentDate = LocalDate.now();

            if (order.getCreate_date() != null) {
                LocalDate createDate = order.getCreate_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long daysBetween = ChronoUnit.DAYS.between(createDate, currentDate);

                if (daysBetween > 90) {
                    throw new OrderCancellationNotAllowedException("O pedido não pode ser cancelado, pois tem mais de 90 dias de criação.");
                }
            } else {
                throw new OrderCancellationNotAllowedException("A data de criação do pedido é nula.");
            }

            order.setStatus(StatusOrderEnum.CANCELED);
            order.setCancel_reason(cancelOrderRequest.getCancelReason());

            Date cancelDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            order.setCancel_date(cancelDate);

            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Pedido não encontrado");
        }
    }
}