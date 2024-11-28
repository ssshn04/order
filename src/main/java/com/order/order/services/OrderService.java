package com.order.order.services;

import com.order.order.entities.Order;
import com.order.order.entities.OrderItem;
import com.order.order.client.ProductClient;
import com.order.order.repos.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public Order createOrder(List<Integer> productIds) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (Integer productId : productIds) {
            if (productClient.isProductInStock(productId)) {
                BigDecimal productPrice = getProductPrice(productId);
                totalPrice = totalPrice.add(productPrice);

                OrderItem orderItem = OrderItem.builder()
                        .productId(productId)
                        .quantity(1)
                        .price(productPrice)
                        .build();
                orderItems.add(orderItem);
            } else {
                log.warn("Product {} is out of stock", productId);
                throw new RuntimeException("Product " + productId + " is out of stock");
            }
        }

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status("CREATED")
                .totalPrice(totalPrice)
                .orderItems(orderItems)
                .build();

        Order savedOrder = orderRepository.save(order);
        log.info("Order {} created successfully", savedOrder.getId());

        return savedOrder;
    }

    private BigDecimal getProductPrice(Integer productId) {
        return productClient.getProductPrice(productId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
