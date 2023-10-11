package com.compassuol.sp.challenge.msproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
}
