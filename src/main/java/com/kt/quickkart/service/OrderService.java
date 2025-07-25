package com.kt.quickkart.service;

import com.kt.quickkart.dto.OrderItemDTO;
import com.kt.quickkart.dto.OrderItemSummaryDTO;
import com.kt.quickkart.dto.OrderRequestDTO;
import com.kt.quickkart.dto.OrderResponseDTO;
import com.kt.quickkart.entity.OrderItem;
import com.kt.quickkart.entity.Orders;
import com.kt.quickkart.entity.Product;
import com.kt.quickkart.entity.User;
import com.kt.quickkart.repository.OrdersRepo;
import com.kt.quickkart.repository.ProductRepo;
import com.kt.quickkart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrdersRepo ordersRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ProductRepo productRepository;


    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(LocalDate.now().toString());
        order.setStatus("CONFIRMED");

        double totalAmount = 0.0;
        List<OrderItem> items = new ArrayList<>();

        for (OrderRequestDTO.Item itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int qty = itemReq.getQuantity();
            double price = product.getPrice() * qty;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(qty);
            orderItem.setPrice(price);

            items.add(orderItem);
            totalAmount += price;
        }

        order.setOrderItems(items);
        order.setAmount(totalAmount);

        Orders savedOrder = ordersRepository.save(order);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(savedOrder.getOrderId());
        response.setAmount(savedOrder.getAmount());
        response.setStatus(savedOrder.getStatus());
        response.setOrderDate(savedOrder.getOrderDate());

        List<OrderItemSummaryDTO> summary = savedOrder.getOrderItems().stream()
                .map(item -> {
                    OrderItemSummaryDTO dto = new OrderItemSummaryDTO();
                    dto.setProductId(item.getProduct().getProductId());
                    dto.setProductName(item.getProduct().getName());
                    dto.setQuantity(item.getQuantity());
                    dto.setPrice(item.getPrice());
                    return dto;
                }).collect(Collectors.toList());

        response.setItems(summary);
        return response;
    }
}
