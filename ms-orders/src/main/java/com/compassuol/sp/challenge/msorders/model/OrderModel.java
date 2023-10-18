package com.compassuol.sp.challenge.msorders.model;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_tb")
@NoArgsConstructor
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
    @Column(precision = 10, scale = 2)
    private Double subtotal_value;
    private Double discount;
    @Column(precision = 10, scale = 2)
    private Double total_value;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime create_date;
    @Enumerated(EnumType.STRING)
    private StatusOrderEnum status;
    private String cancel_reason;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("cancel_date")
    private LocalDateTime cancel_date;

    public OrderModel(List<OrderProductsModel> products, AddressModel address,
                      PaymentTypeEnum payment_method, Double subtotal_value,
                      StatusOrderEnum status, String cancel_reason) throws ParseException {
        this.products = products;
        this.address = address;
        this.payment_method = payment_method;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String stringDouble = decimalFormat.format(subtotal_value);
        this.subtotal_value = decimalFormat.parse(stringDouble).doubleValue();
        this.status = status;
        this.cancel_reason = cancel_reason;
        this.create_date = LocalDateTime.now();

        if (!this.cancel_reason.isEmpty()) {
            this.cancel_date = LocalDateTime.now();
            this.status = StatusOrderEnum.CANCELED;
        }

        double percentage;

        if (this.getPayment_method() == PaymentTypeEnum.PIX) {
            this.discount = 0.05;
            percentage = this.subtotal_value*this.discount;
            this.total_value = decimalFormat.parse(stringDouble).doubleValue() - percentage;
        } else {
            this.discount = 0.0;
            this.total_value = decimalFormat.parse(stringDouble).doubleValue();
        }
    }
}
