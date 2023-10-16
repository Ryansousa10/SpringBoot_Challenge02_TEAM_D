package com.compassuol.sp.challenge.msorders.dto;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateOrderDTO {
    @NotNull(message = "Status field cannot be null")
    @Size(min = 1, message = "Status must be provided")
    private StatusOrderEnum status;

    private List<ProductUpdateDTO> products;  // Adicione a lista de produtos

    private AddressDTO address;  // Adicione o endereço

    private String paymentMethod;  // Adicione o método de pagamento

    // Implemente os getters para os novos campos
    public List<ProductUpdateDTO> getProducts() {
        return products;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
