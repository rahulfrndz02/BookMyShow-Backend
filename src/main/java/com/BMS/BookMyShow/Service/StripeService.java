//package com.BMS.BookMyShow.Service;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import org.hibernate.mapping.Map;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//
//@Service
//public class StripeService {
//
//    @Value("${stripe.apiKey}")
//    private String secretKey;
//
//    public void chargeCreditCard(String customerId, int amount) throws StripeException {
//        Stripe.apiKey = secretKey;
//
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("amount", amount);
//        params.put("currency", "usd");
//        params.put("customer", customerId); // Assuming you have a Stripe customer ID for each user
//        params.put("description", "Ticket purchase");
//
//        Charge.create(params);
//    }
//}
//
