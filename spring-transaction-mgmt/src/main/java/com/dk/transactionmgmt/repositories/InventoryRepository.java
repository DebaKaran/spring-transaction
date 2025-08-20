package com.dk.transactionmgmt.repositories;

import com.dk.transactionmgmt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Product,Integer> {
}