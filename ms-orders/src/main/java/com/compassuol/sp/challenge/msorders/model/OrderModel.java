package com.compassuol.sp.challenge.msorders.model;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long id;
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
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedAt;  // Novo campo para data de atualização
    @Enumerated(EnumType.STRING)
    private StatusOrderEnum status;
    private String cancel_reason = "";
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date cancel_date;

    public OrderModel(List<OrderProductsModel> products, AddressModel address,
                      PaymentTypeEnum payment_method, Double subtotal_value, StatusOrderEnum status) {
        this.products = products;
        this.address = address;
        this.payment_method = payment_method;
        this.subtotal_value = subtotal_value;
        this.status = status;

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

    public void setPaymentMethod(String paymentMethod) {
    }

    public void setUpdateDate(Date date) {
    }

    public void setUpdatedAt(String format) {
    }
}
