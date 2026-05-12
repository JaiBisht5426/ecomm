package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.CartItem;
import com.ecommerce.ecomm.repository.CartRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final CartRepository cartRepository;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    public PaymentController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostMapping("/create-order")
    public String createOrder(Authentication auth) throws Exception {

        String email = auth.getName();

        List<CartItem> cartItems =
                cartRepository.findByUserEmail(email);

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cartItems) {

            BigDecimal itemTotal =
                    item.getProduct()
                            .getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));

            total = total.add(itemTotal);
        }

        // 🔥 convert rupees → paise
        int amount = total.multiply(BigDecimal.valueOf(100)).intValue();

        RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", amount);

        orderRequest.put("currency", "INR");

        orderRequest.put("receipt", "txn_123456");

        Order order = client.orders.create(orderRequest);

        return order.toString();
    }
}


//


