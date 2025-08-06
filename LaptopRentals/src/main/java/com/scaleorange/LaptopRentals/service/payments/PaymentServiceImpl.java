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
        RazorpayOrderResponse response = new RazorpayOrderResponse();
        response.setId(order.get("id"));
        response.setAmount(order.get("amount"));
        response.setAmount_paid(order.get("amount_paid"));
        response.setAmount_due(order.get("amount_due"));
        response.setCurrency(order.get("currency"));
        response.setReceipt(order.get("receipt"));
        response.setStatus(order.get("status"));
        response.setAttempts(order.get("attempts"));
        response.setCreated_at(order.get("created_at"));

        return response;

//      return  Mapper.convertToPaymentRespo(
//        razorpayClient.orders.create(options));

    }
}
