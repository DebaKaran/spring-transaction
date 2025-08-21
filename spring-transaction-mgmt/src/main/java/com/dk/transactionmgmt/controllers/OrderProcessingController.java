package com.dk.transactionmgmt.controllers;

import com.dk.transactionmgmt.entities.Order;
import com.dk.transactionmgmt.services.OrderProcessingService;
import com.dk.transactionmgmt.services.isolations.ReadUncommittedDemo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;
    private final ReadUncommittedDemo readUncommittedDemo;

    public OrderProcessingController(OrderProcessingService orderProcessingService,
                                     ReadUncommittedDemo readUncommittedDemo) {
        this.orderProcessingService = orderProcessingService;
        this.readUncommittedDemo = readUncommittedDemo;
    }

    /**
     * API to place an order
     *
     * @param order the order details
     * @return the processed order with updated total price
     */
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderProcessingService.placeAnOrder(order));
    }

    @GetMapping("/isolation")
    public String testIsolation() throws InterruptedException {
        readUncommittedDemo.testReadUncommitted(1);
        return "success";
    }
}
