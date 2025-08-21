package com.dk.transactionmgmt.seatbooking.controllers;


import com.dk.transactionmgmt.seatbooking.services.OptimisticSeatBookingTestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingTestController {

    private final OptimisticSeatBookingTestService optimisticSeatBookingTestService;

    private BookingTestController(OptimisticSeatBookingTestService optimisticSeatBookingTestService) {
        this.optimisticSeatBookingTestService = optimisticSeatBookingTestService;
    }
    @GetMapping("/optimistic/{seatId}")
    public String testOptimistic(@PathVariable Long seatId) throws InterruptedException {
        optimisticSeatBookingTestService.testOptimisticLocking(seatId);
        return "Optimistic locking test started! Check logs for results.";
    }

    @GetMapping("/pessimistic/{seatId}")
    public String testPessimistic(@PathVariable Long seatId) throws InterruptedException {

        return "Pessimistic locking test started! Check logs for results.";
    }
}