package com.scaleorange.LaptopRentals.service.payments;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayOrderResponse;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayRequest;
import com.scaleorange.LaptopRentals.utils.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentsService{

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Override
    public RazorpayOrderResponse makePaymentOrder(RazorpayRequest request) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject options = new JSONObject();
        // Razorpay requires paise
        options.put("amount", (int)(request.getAmount() * 100));
        options.put("currency", "INR");
        options.put("receipt", "order_rcptid_" + request.getOrderId());
        Order order = razorpayClient.orders.create(options);

//        RazorpayOrderResponse response = new RazorpayOrderResponse();
//        response.setId(order.get("id"));
//        response.setAmount(order.get("amount"));
//        response.setAmount_paid(order.get("amount_paid"));
//        response.setAmount_due(order.get("amount_due"));
//        response.setCurrency(order.get("currency"));
//        response.setReceipt(order.get("receipt"));
//        response.setStatus(order.get("status"));
//        response.setAttempts(order.get("attempts"));
//        response.setCreated_at(order.get("created_at"));

        RazorpayOrderResponse dto = new RazorpayOrderResponse();

        dto.setId((String) order.get("id"));
        dto.setCurrency((String) order.get("currency"));
        dto.setReceipt((String) order.get("receipt"));
        dto.setStatus((String) order.get("status"));
        dto.setAttempts((int) order.get("attempts"));

// Safely convert to Double from Integer
        dto.setAmount(((Number) order.get("amount")).doubleValue());
        dto.setAmount_paid(((Number) order.get("amount_paid")).doubleValue());
        dto.setAmount_due(((Number) order.get("amount_due")).doubleValue());

// Convert timestamp (epoch seconds) to java.util.Date
        long timestamp = ((Number) order.get("created_at")).longValue();
        dto.setCreated_at(new Date(timestamp * 1000)); // convert to milliseconds

        return dto;

//      return  Mapper.convertToPaymentRespo(
//        razorpayClient.orders.create(options));

    }
}
