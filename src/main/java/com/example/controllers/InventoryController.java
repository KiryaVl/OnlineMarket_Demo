package com.example.controllers;

import com.example.services.FileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.models.InventoryItem;
import com.example.repositoryes.InventoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;



    private final FileGateway fileGateway;

    private LocalDateTime localDateTime;

    /**
     * Метод создания товара и добавления его на "склад"
     * @param inventoryItem
     * @return Создание единицы товара
     */
    @PostMapping("/add")
    public ResponseEntity<String> addProductToInventory(@RequestBody InventoryItem inventoryItem) {
        String time =  LocalDateTime.now().toString();
        inventoryRepository.save(inventoryItem);
        fileGateway.writeToFile("Items.txt", inventoryItem.getName() + " item added in: " + time);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to inventory successfully.");
    }

    /**
     * Метод вывода всех единиц товара на складе
     * @return Список товаров
     */
    @GetMapping("/list")
    public ResponseEntity<List<InventoryItem>> getAllProducts() {
        List<InventoryItem> productList = inventoryRepository.findAll();
        return ResponseEntity.ok(productList);
    }
}
