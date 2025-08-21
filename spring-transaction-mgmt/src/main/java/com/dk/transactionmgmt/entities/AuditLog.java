package com.dk.transactionmgmt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;  // The order associated with the log

    private String action;  // Action taken (e.g., "Order Placed", "Payment Failed")

    private LocalDateTime timestamp;  // Timestamp of the action

    // Default constructor
    public AuditLog() {
        this.timestamp = LocalDateTime.now();  // Default timestamp is the current time
    }

}
