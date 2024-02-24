package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.models.InventoryItem;
import com.example.repositoryes.InventoryRepository;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToInventory(@RequestBody InventoryItem inventoryItem) {
        inventoryRepository.save(inventoryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to inventory successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<InventoryItem>> getAllProducts() {
        List<InventoryItem> productList = inventoryRepository.findAll();
        return ResponseEntity.ok(productList);
    }
}
