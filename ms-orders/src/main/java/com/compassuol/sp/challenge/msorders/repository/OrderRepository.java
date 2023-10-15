package com.compassuol.sp.challenge.msorders.repository;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
