package com.compassuol.sp.challenge.msproducts.constants;

import com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import com.compassuol.sp.challenge.msproducts.model.ProductModel;

public class ProductsConstants {
    public static final ProductDTO VALID_PRODUCT_DTO = new ProductDTO("camo pants",
            "very good camo pants", 45.90);
    public static final ProductModel VALID_PRODUCT = new ProductModel("camo pants",
            "very good camo pants", 1.50);
    public static final ProductDTO INVALID_PRODUCT_DTO = new ProductDTO("", "", -1.50);
    public static final ProductModel INVALID_PRODUCT = new ProductModel("", "", -1.50);
}
