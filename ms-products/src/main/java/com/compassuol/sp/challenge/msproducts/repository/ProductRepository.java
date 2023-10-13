package com.compassuol.sp.challenge.msproducts.repository;

import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProductModel p WHERE p.name = ?1")
    boolean existsByName(String name);

}
