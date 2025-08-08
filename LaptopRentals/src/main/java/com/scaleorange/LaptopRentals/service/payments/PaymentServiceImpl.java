package com.scaleorange.LaptopRentals.service.payments;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.PaymentRequest;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayResponse;
import com.scaleorange.LaptopRentals.entity.PaymentType;
import com.scaleorange.LaptopRentals.entity.Payments;
import com.scaleorange.LaptopRentals.repo.PaymentsRepo;
import com.scaleorange.LaptopRentals.repo.RentalsRepo;


import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentsService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;
    @Autowired
    private PaymentsRepo paymentsRepo;
    @Autowired
    private RentalsRepo rentalsRepo;

    @Override
    public Payments makeRazorpayOrder(PaymentRequest request) throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject options = new JSONObject();
        // Razorpay requires paise
        options.put("amount", (int) (request.getAmount() * 100));
        options.put("currency", "INR");
        options.put("receipt", "order_rcptid_" + request.getRentalId() + "_" + request.getPaymentType().name());

        Order order = razorpayClient.orders.create(options);

        Payments payment = new Payments();

        payment.setAmount(((Number) order.get("amount")).doubleValue());
        payment.setRazorpayOrderId(order.get("id"));
        payment.setStatus((String) order.get("status"));
        payment.setPaymentDate(new Date());
        payment.setPaymentType(request.getPaymentType());

        payment.setRentals(rentalsRepo.findById(request.getRentalId()).orElseThrow(() -> new RuntimeException("Invalid Rental Id")));

        return paymentsRepo.save(payment);

    }

    @Override
    public Boolean verifyPayment(RazorpayResponse response) throws Exception {

            String data = response.getRazorpayOrderId() + "|" + response.getRazorpayPaymentId();
            String expectedSignature = hmacSHA256(data, razorpaySecret);

            if (!expectedSignature.equals(response.getRazorpaySignature())) {
                return false;
            }

            // 1. Find payment by orderId
            Payments payment = paymentsRepo.findByRazorpayOrderId(response.getRazorpayOrderId()).orElseThrow(() -> new RuntimeException("Payment record not found"));

            // 2. Update payment info
            payment.setRazorpayPaymentId(response.getRazorpayPaymentId());
            payment.setRazorpaySignature(response.getRazorpaySignature());
            payment.setPaymentType(PaymentType.FINAL);

            payment.setStatus("SUCCESS");

            paymentsRepo.save(payment);
            return true;

    }

    private String hmacSHA256(String data, String secret) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes());
        return Hex.encodeHexString(hash);
    }

}
