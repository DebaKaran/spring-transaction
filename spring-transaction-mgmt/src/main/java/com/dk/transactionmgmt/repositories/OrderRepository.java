package com.dk.transactionmgmt.repositories;

import com.dk.transactionmgmt.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}