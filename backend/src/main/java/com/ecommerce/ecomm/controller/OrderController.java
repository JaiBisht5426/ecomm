package com.ecommerce.ecomm.controller;


import com.ecommerce.ecomm.dto.CheckoutRequest;
import com.ecommerce.ecomm.model.*;
import com.ecommerce.ecomm.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(CartRepository cartRepository,
                           OrderRepository orderRepository,
                           UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

//    @GetMapping("/orderhistory")
//    public List<List<OrderItem>> orderhistory(Authentication auth)
//    {
//        String email = auth.getName();
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
//        List<Order> orders = orderRepository.findByUser(user);
//        List<List<OrderItem>> oii = new ArrayList<>();
//        for(Order ord: orders)
//        {
//            List<OrderItem> oi= ord.getItems();
//            oii.add(oi);
//        }
//        return oii;
//    }

    @GetMapping("/my-orders")
    public List<Order> getMyOrders(Authentication auth)
    {
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUser(user);
    }

    // ✅ CHECKOUT API
    @PostMapping("/checkout")
    public String checkout(@RequestBody CheckoutRequest request, Authentication auth) {

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // ✅ use email
        List<CartItem> cartItems =
                cartRepository.findByUserEmail(email);

        if (cartItems.isEmpty()) {
            return "Cart is empty ❌";
        }

        Order order = new Order();

        order.setUser(user);

        order.setStatus("ORDER_PLACED");

        order.setOrderDate(LocalDateTime.now());

        order.setFullName(
                request.getAddress().getFullName());

        order.setPhone(
                request.getAddress().getPhone());

        order.setCity(
                request.getAddress().getCity());

        order.setState(
                request.getAddress().getState());

        order.setPincode(
                request.getAddress().getPincode());

        order.setAddressLine(
                request.getAddress().getAddressLine());

        order.setPaymentMethod(
                request.getPaymentMethod());

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cart : cartItems) {

            OrderItem item = new OrderItem();

            item.setProductName(cart.getProduct().getName());

            item.setPrice(cart.getProduct().getPrice());

            item.setQuantity(cart.getQuantity());

            item.setImageUrl(cart.getProduct().getImageUrl());

            orderItems.add(item);

            BigDecimal itemTotal =
                    cart.getProduct()
                            .getPrice()
                            .multiply(BigDecimal.valueOf(cart.getQuantity()));

            total = total.add(itemTotal);
        }

        order.setItems(orderItems);

        order.setTotalAmount(total);

        orderRepository.save(order);

        cartRepository.deleteAll(cartItems);

        return "Order Placed Successfully ✅";
    }
}
