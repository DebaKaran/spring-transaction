package com.dk.transactionmgmt.services;

import com.dk.transactionmgmt.entities.Order;
import com.dk.transactionmgmt.entities.Product;
import com.dk.transactionmgmt.handlers.AuditLogHandler;
import com.dk.transactionmgmt.handlers.InventoryHandler;
import com.dk.transactionmgmt.handlers.OrderHandler;
import com.dk.transactionmgmt.handlers.PaymentValidatorHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {

    private final OrderHandler orderHandler;

    private final InventoryHandler inventoryHandler;

    private final AuditLogHandler auditLogHandler;

    private final PaymentValidatorHandler paymentValidatorHandler;

    public OrderProcessingService(OrderHandler orderHandler,
                                  InventoryHandler inventoryHandler,
                                  AuditLogHandler auditLogHandler,
                                  PaymentValidatorHandler paymentValidatorHandler) {
        this.orderHandler = orderHandler;
        this.inventoryHandler = inventoryHandler;
        this.auditLogHandler = auditLogHandler;
        this.paymentValidatorHandler = paymentValidatorHandler;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeAnOrder(Order order) {
        // 1: get product from inventory
        Product product =  inventoryHandler.getProduct(order.getProductId());

        // 2: validate stock availability <(5)
        if(order.getQuantity() > product.getStockQuantity()) {
            throw new RuntimeException("insufficent stock available");
        }

        //3: update total price in order entity
        order.setTotalPrice(order.getQuantity() * product.getPrice());
        Order savedOrder = null;

        try {
            //4: save order
            savedOrder = orderHandler.saveOrder(order);

            //5: update stock in inventory
            updateInventoryStock(order, product);

            auditLogHandler.logAuditDetails(order, "Order placement succeed...");
        } catch (Exception e){
            auditLogHandler.logAuditDetails(order, "Order placement failed...");
        }

        paymentValidatorHandler.validatePayment(order);
        return savedOrder;
    }

    private void updateInventoryStock(Order order, Product product) {
        int availableStock = product.getStockQuantity() - order.getQuantity();
        product.setStockQuantity(availableStock);
        inventoryHandler.updateProductDetails(product);
    }
}
