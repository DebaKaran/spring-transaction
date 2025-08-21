package com.dk.transactionmgmt.handlers;

import com.dk.transactionmgmt.entities.AuditLog;
import com.dk.transactionmgmt.entities.Order;
import com.dk.transactionmgmt.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditLogHandler {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Log audit details (runs in an independent transaction)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAuditDetails(Order order, String action) {
        AuditLog auditLog = new AuditLog();
        auditLog.setOrderId((long) order.getId());
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());

        // Save the audit log
        auditLogRepository.save(auditLog);
    }
}
