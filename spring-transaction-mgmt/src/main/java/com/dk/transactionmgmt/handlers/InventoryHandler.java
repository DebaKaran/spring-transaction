package com.dk.transactionmgmt.handlers;

import com.dk.transactionmgmt.entities.Product;
import com.dk.transactionmgmt.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryHandler {


    private final InventoryRepository inventoryRepository;

    public InventoryHandler(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    public Product updateProductDetails(Product product) {

        //forcefully throwing exception to simulate use of tx
        if(product.getPrice() > 50000){
            throw new RuntimeException("DB crashed.....");
        }
        return inventoryRepository.save(product);
    }


    public Product getProduct(int id) {
        return inventoryRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product not available with id : " + id)
                );
    }
}