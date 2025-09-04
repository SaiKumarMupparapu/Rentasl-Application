package com.scaleorange.LaptopRentals.controller.payments;


import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.PaymentRequest;

import com.scaleorange.LaptopRentals.dto.payments.RazorpayResponse;
import com.scaleorange.LaptopRentals.entity.Payments;
import com.scaleorange.LaptopRentals.repo.PaymentsRepo;
import com.scaleorange.LaptopRentals.service.payments.PaymentsService;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

@RestController
@RequestMapping("/razor")
//@PreAuthorize("hasAuthority('COMPANY')")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @PostMapping(value = "/pay", produces = "application/json")
    public ResponseEntity<Payments> payment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Payments payments = paymentsService.makeRazorpayOrder(paymentRequest);

            return new ResponseEntity<>(payments, HttpStatus.CREATED);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }




    @PostMapping("/payments/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayResponse response) {
        try {
            Boolean status = paymentsService.verifyPayment(response);
            if (!status) {
                return ResponseEntity.status(400).body("Invalid payment signature!");
            }
            return ResponseEntity.ok("Payment verified successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error verifying payment");
        }
    }
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;

    @Autowired
    private PaymentsRepo paymentsRepo;

//    LoggerFactory.getLogger(PaymentsController.class);

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
                                                @RequestHeader("X-Razorpay-Signature") String razorpaySignature) {



        try {
            // ✅ Verify webhook signature
            if (!verifyWebhookSignature(payload, razorpaySignature)) {
                System.err.println("invalid sign ");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
            }

            // ✅ Parse event
            JSONObject event = new JSONObject(payload);
            String eventType = event.getString("event");

            if ("payment.captured".equals(eventType)) {


                JSONObject payloadObj = event.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
                String orderId = payloadObj.getString("order_id");
                String paymentId = payloadObj.getString("id");
                String status = payloadObj.getString("status");

                System.out.println(orderId);

                System.out.println(paymentId);

                System.out.println(status);

                // ✅ Update DB
                Payments payment = paymentsRepo.findByRazorpayOrderId(orderId)
                        .orElseThrow(() -> new RuntimeException("Payment not found"));

                payment.setRazorpayPaymentId(paymentId);
                payment.setStatus(status.toUpperCase());
                payment.setRazorpaySignature(razorpaySignature);
                paymentsRepo.save(payment);
            }
            System.out.println(" the webhook method completed");

            return ResponseEntity.ok("Webhook processed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    private boolean verifyWebhookSignature(String payload, String actualSignature) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(webhookSecret.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKey);

        byte[] hash = sha256Hmac.doFinal(payload.getBytes());

        // Convert hash bytes to HEX
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        String generatedSignature = sb.toString();

        return generatedSignature.equals(actualSignature);
    }
}



