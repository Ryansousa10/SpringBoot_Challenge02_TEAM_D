package com.compassuol.sp.challenge.msorders.constants;


import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.dto.AddressRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.ProductModelDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.dto.ViaCepAddressDTO;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;

import java.util.List;

public class ConstantOrders {
    public static List<OrderProductsModel> orderProductsModel = List.of(new OrderProductsModel(1L, 2L));
    public static AddressRequestDTO addressRequestDTO = new AddressRequestDTO("street", 1
            , "01001-000");
    public static final RequestOrderDTO REQUEST_ORDER_DTO = new RequestOrderDTO(orderProductsModel, addressRequestDTO,
            PaymentTypeEnum.CREDIT_CARD);
    public static final ProductModelDTO PRODUCT_MODEL_DTO = new ProductModelDTO(1L, "t-shirt",
            "description", 20.50);
    public static final ViaCepAddressDTO VIA_CEP_ADDRESS_DTO = new ViaCepAddressDTO(
            "3423423", "324234234", "4234234", "45345345", "44535345",
            "345345345");
}
