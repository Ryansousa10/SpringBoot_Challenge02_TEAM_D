package com.compassuol.sp.challenge.msorders.order.repository;

import com.compassuol.sp.challenge.msorders.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}