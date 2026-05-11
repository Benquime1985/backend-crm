package com.optica.backend_crm.controller;


import com.optica.backend_crm.models.Inventory;
import com.optica.backend_crm.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/all")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getStock(@PathVariable Integer id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/alerts")
    public List<Inventory> getLowStockAlerts() {
        return inventoryService.getAlerts();
    }

    @GetMapping("/count-low-stock")
    public long getLowStockCount() {
        return inventoryService.getLowStockCount();
    }

    @PostMapping("/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
    }

    @PutMapping("/update/{id}")
    public Inventory updateInventory(@PathVariable Integer id, @RequestBody Inventory details) {
        return inventoryService.updateInventory(id, details);
    }



}
