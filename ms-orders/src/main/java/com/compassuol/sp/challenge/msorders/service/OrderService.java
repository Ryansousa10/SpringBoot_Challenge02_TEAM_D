package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msorders.dto.ProductModelDTO;
import com.compassuol.sp.challenge.msorders.dto.RequestOrderDTO;
import com.compassuol.sp.challenge.msorders.dto.ViaCepAddress;
import com.compassuol.sp.challenge.msorders.model.AddressModel;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.compassuol.sp.challenge.msorders.proxy.ProductsProxy;
import com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductsProxy proxy;

    public List<OrderModel> getAllOrdersService() {
        //para implementer
        return orderRepository.findAll();
    }

    public void getOrderByIdService() {
        //para implementer
    }

    public OrderModel createOrderService(RequestOrderDTO request) throws ParseException {
        ViaCepAddress cepObject;
        double subtotalValue = 0.0;
        //sum products value per id
        for (OrderProductsModel productsModel: request.getProducts()) {
            ProductModelDTO product = proxy.getProductById(Long.parseLong(
                    String.valueOf(productsModel.getProduct_id())));
            subtotalValue += product.getValue();
        }
        //get full address
        try {
            String apiUrl = "http://viacep.com.br/ws/"+ request.getAddress().getPostalCode() + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            String object = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            cepObject = objectMapper.readValue(object, ViaCepAddress.class);
        } catch (BusinessErrorException | JsonProcessingException ex) {
            throw new BusinessErrorException("postalCode has an error");}
        //set fields
        assert cepObject != null;
        AddressModel address = new AddressModel(request.getAddress().getStreet(),
                request.getAddress().getNumber(), cepObject.getComplemento(),
                cepObject.getLocalidade(), cepObject.getUf(), cepObject.getCep());
        //send to database
        OrderModel order = new OrderModel(request.getProducts(), address, request.getPayment_method(), subtotalValue,
                StatusOrderEnum.CONFIRMED, "");
        return orderRepository.save(order);
    }

    public void updateOrderService() {
        //para implementer
    }

    public void cancelOrderByIdService() {
        //para implementer
    }
}
