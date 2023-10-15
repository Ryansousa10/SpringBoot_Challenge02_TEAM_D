package com.compassuol.sp.challenge.msorders.model;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_tb")
@NoArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "orderModelRelation")
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
    private Date create_date;
    @Enumerated(EnumType.STRING)
    private StatusOrderEnum status;
    private String cancel_reason;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date cancel_date;

    public OrderModel(List<OrderProductsModel> products, AddressModel address,
                      PaymentTypeEnum payment_method, Double subtotal_value,
                      StatusOrderEnum status, String cancel_reason) throws ParseException {
        this.products = products;
        this.address = address;
        this.payment_method = payment_method;
        this.subtotal_value = subtotal_value;
        this.status = status;
        this.cancel_reason = cancel_reason;

        if (!this.cancel_reason.isEmpty()) this.cancel_date = DateFormat.getDateInstance()
                .parse(String.valueOf(LocalDateTime.now()));

        Double percentage;

        if (this.getPayment_method() == PaymentTypeEnum.PIX) {
            this.discount = 0.5;
            percentage = ((this.discount*100)/this.subtotal_value);
        } else {
            this.discount = 0.0;
            percentage = this.subtotal_value;
        }

        this.total_value = percentage;
    }
}
