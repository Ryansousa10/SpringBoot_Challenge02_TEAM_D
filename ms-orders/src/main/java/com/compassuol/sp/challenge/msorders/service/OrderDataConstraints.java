package com.compassuol.sp.challenge.msorders.service;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;

public class OrderDataConstraints {

    public double FormatDoubles(double value) throws ParseException {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String stringDouble = decimalFormat.format(value);
        return decimalFormat.parse(stringDouble).doubleValue();
    }

    public HashMap<String, Double> checkPromotion(PaymentTypeEnum paymentType, double subtotal)
            throws ParseException {
        double percentage;
        double discount = 0.0;
        double total_value;

        if (paymentType == PaymentTypeEnum.PIX) {
            discount = 0.05;
            percentage = subtotal*discount;
            total_value = subtotal - percentage;
        } else {
            total_value = subtotal;
        }

        double formattedTotalValue = this.FormatDoubles(total_value);
        HashMap<String, Double> valuesPerPromotion = new HashMap<>();
        valuesPerPromotion.put("discount", discount);
        valuesPerPromotion.put("total_value", formattedTotalValue);

        return valuesPerPromotion;
    }
}
