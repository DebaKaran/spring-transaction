package com.dk.transactionmgmt.handlers;

import com.dk.transactionmgmt.entities.Order;
import com.dk.transactionmgmt.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderHandler {

    private final OrderRepository orderRepository;

    public OrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}