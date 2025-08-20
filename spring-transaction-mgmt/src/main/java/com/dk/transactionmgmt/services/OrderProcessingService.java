package com.dk.transactionmgmt.services;

import com.dk.transactionmgmt.entities.Order;
import com.dk.transactionmgmt.handlers.InventoryHandler;
import com.dk.transactionmgmt.handlers.OrderHandler;

public class OrderProcessingService {

    private final OrderHandler orderHandler;

    private final InventoryHandler inventoryHandler;

    public OrderProcessingService(OrderHandler orderHandler, InventoryHandler inventoryHandler) {
        this.orderHandler = orderHandler;
        this.inventoryHandler = inventoryHandler;
    }

    public Order placeAnOrder(Order order) {
        return null;
    }
}
