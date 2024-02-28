package com.example.services;

import com.example.exceptions.NotFoundException;
import com.example.exceptions.InsufficientStockException;
import com.example.models.InventoryItem;
import com.example.repositoryes.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarehouseService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void reserveProduct(Long productId, int quantity) {
        InventoryItem item = inventoryRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        if (item.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock for product: " + productId);
        }
        item.setQuantity(item.getQuantity() - quantity);
        inventoryRepository.save(item);
    }

}
