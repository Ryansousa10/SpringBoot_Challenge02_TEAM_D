package com.compassuol.sp.challenge.msorders.repository;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    @Query("SELECT o FROM OrderModel o ORDER BY o.create_date DESC")
    List<OrderModel> findOrdersByCreateDateDesc();

    @Query("SELECT o FROM OrderModel o WHERE o.status = :status ORDER BY o.create_date DESC")
    List<OrderModel> findOrdersByStatusAndCreateDateDesc(@Param("status") StatusOrderEnum status);
}
