package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import com.compassuol.sp.challenge.msorders.dto.CancelOrderRequestDTO;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
        Optional<OrderModel> optionalOrder = orderRepository.findById(Math.toIntExact(id));

        if (optionalOrder.isPresent()) {
            OrderModel order = optionalOrder.get();

            if (order.getStatus() == StatusOrderEnum.SENT) {
                throw new OrderCancellationNotAllowedException("O pedido não pode ser cancelado, pois já foi enviado.");
            }

            LocalDateTime currentDateTime = LocalDateTime.now();

            if (order.getCreate_date() != null) {
                LocalDateTime createDateTime = order.getCreate_date()
                        .toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now()))
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                long daysBetween = ChronoUnit.DAYS.between(createDateTime, currentDateTime);

                if (daysBetween > 90) {
                    throw new OrderCancellationNotAllowedException("O pedido não pode ser cancelado, pois tem mais de 90 dias de criação.");
                }
            } else {
                throw new OrderCancellationNotAllowedException("A data de criação do pedido é nula.");
            }

            order.setStatus(StatusOrderEnum.CANCELED);
            order.setCancel_reason(cancelOrderRequest.getCancelReason());

            order.setCancel_date(currentDateTime);

            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Pedido não encontrado");
        }
    }
}