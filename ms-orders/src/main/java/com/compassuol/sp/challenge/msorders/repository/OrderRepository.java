package com.compassuol.sp.challenge.msorders.repository;

import com.compassuol.sp.challenge.msorders.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
