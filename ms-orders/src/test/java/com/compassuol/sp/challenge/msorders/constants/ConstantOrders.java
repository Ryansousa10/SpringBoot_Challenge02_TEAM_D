package com.compassuol.sp.challenge.msorders.constants;


import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.dto.*;
import com.compassuol.sp.challenge.msorders.model.AddressModel;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;

import java.time.LocalDateTime;
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
            "01001-000", "324234234", "4234234", "45345345", "44535345",
            "345345345");
    public static final AddressModel ADDRESS_MODEL = new AddressModel(1L, "street", 1,
            "4234234", "4234234", "44535345", "345345345");
    public static final OrderModel ORDER_RESPONSE = new OrderModel(1L, orderProductsModel, ADDRESS_MODEL,
            PaymentTypeEnum.CREDIT_CARD, 20.50, 0.0, 20.50, LocalDateTime.now(),
            StatusOrderEnum.CONFIRMED, "", null);
    public static final CreateOrderResponseDTO CREATE_ORDER_RESPONSE_DTO = new CreateOrderResponseDTO(ORDER_RESPONSE);
}
