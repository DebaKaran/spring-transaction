package com.dk.transactionmgmt.repositories;

import com.dk.transactionmgmt.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
