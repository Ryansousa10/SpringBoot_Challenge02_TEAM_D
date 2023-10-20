package com.compassuol.sp.challenge.msorders.model;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.service.OrderDataConstraints;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_tb")
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "order_products_tb", joinColumns = @JoinColumn(name = "principal_class_id"))
    private List<OrderProductsModel> products;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foreign_address_id")
    private AddressModel address;
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum payment_method;
    private Double subtotal_value;
    private Double discount;
    private Double total_value;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime create_date;
    @Enumerated(EnumType.STRING)
    private StatusOrderEnum status;
    private String cancel_reason;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime cancel_date;

    public OrderModel(List<OrderProductsModel> products, AddressModel address,
                      PaymentTypeEnum payment_method, Double subtotal_value,
                      StatusOrderEnum status, String cancel_reason) throws ParseException {
        HashMap<String, Double> checkPromo = new OrderDataConstraints().checkPromotion(payment_method, subtotal_value);
        this.products = products;
        this.address = address;
        this.payment_method = payment_method;
        this.subtotal_value = new OrderDataConstraints().FormatDoubles(subtotal_value);
        this.status = status;
        this.cancel_reason = cancel_reason;
        this.create_date = LocalDateTime.now();
        this.discount = checkPromo.get("discount");
        this.total_value = checkPromo.get("total_value");

        if (!this.cancel_reason.isEmpty()) {
            this.cancel_date = LocalDateTime.now();
            this.status = StatusOrderEnum.CANCELED;
        }
    }
}
