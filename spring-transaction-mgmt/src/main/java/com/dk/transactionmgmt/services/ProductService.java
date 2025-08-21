package com.dk.transactionmgmt.services;

import com.dk.transactionmgmt.entities.Product;
import com.dk.transactionmgmt.repositories.InventoryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class ProductService {

    @Autowired
    private InventoryRepository inventoryRepository;

    //Hibernate might cache certain queries.
    // Flush after each query using entityManager.flush().

    @Autowired
    private EntityManager entityManager;

    // Transaction A:
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateStock(int productId, int stock) throws InterruptedException {

        // Retrieve the product and update its stock

        Product product = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStockQuantity(stock);

        inventoryRepository.save(product);
        entityManager.flush(); // Ensure the update is sent to the DB

        // Simulate a long-running transaction (does not commit yet)
        System.out.println("Transaction A: Stock updated to " + stock);
        Thread.sleep(5000);

        System.out.println("Transaction A: Rolling back the update");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//explicit rollback

        System.out.println("Transaction A: Committed the update");
    }

    // Transaction B: Read stock
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public int checkStock(int productId) {

        // Retrieve the product and read its stock (potentially dirty read)
        Product product = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        System.out.println("Transaction B: Read stock as " + product.getStockQuantity());
        return product.getStockQuantity();
    }
}
