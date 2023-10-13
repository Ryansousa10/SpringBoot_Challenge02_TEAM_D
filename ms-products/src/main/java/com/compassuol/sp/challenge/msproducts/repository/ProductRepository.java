package com.compassuol.sp.challenge.msproducts.repository;

import com.compassuol.sp.challenge.msproducts.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
