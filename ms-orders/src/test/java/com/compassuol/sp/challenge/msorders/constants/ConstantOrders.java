package com.compassuol.sp.challenge.msorders.constants;


import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.dto.AddressRequestDTO;
import com.compassuol.sp.challenge.msorders.dto.ProductModelDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.dto.ViaCepAddress;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;

import java.util.List;

public class ConstantOrders {
    public static final String VIA_CEP = """
               {
                  "cep": "01001-000",
                  "logradouro": "Praça da Sé",
                  "complemento": "lado ímpar",
                  "bairro": "Sé",
                  "localidade": "São Paulo",
                  "uf": "SP",
                  "ibge": "3550308",
                  "gia": "1004",
                  "ddd": "11",
                  "siafi": "7107"
                }
            """;
    public static List<OrderProductsModel> orderProductsModel = List.of(new OrderProductsModel(1L, 2L));
    public static AddressRequestDTO addressRequestDTO = new AddressRequestDTO("street", 1
            , "01001-000");
    public static final RequestOrderDTO REQUEST_ORDER_DTO = new RequestOrderDTO(orderProductsModel, addressRequestDTO,
            PaymentTypeEnum.CREDIT_CARD);
    public static final ProductModelDTO PRODUCT_MODEL_DTO = new ProductModelDTO(1L, "t-shirt",
            "description", 20.50);
    public static final ViaCepAddress VIA_CEP_ADDRESS = new ViaCepAddress("01001-000", "Praça da Sé",
            "lado ímpar", "Sé", "São Paulo",
            "SP", "3550308", "1004", "11", "7107");

}
