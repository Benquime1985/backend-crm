package com.optica.backend_crm.services;


import com.optica.backend_crm.models.Inventory;
import com.optica.backend_crm.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAlerts() {
        return inventoryRepository.findLowStockProducts();
    }

    public long getLowStockCount() {
        return inventoryRepository.findLowStockProducts().size();
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory updateInventory(Integer id, Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lentes no se encuentran con el ID: " + id));

        if (inventoryDetails.getSku() != null)
            inventory.setSku(inventoryDetails.getSku());
        if (inventoryDetails.getBrand() != null)
            inventory.setBrand(inventoryDetails.getBrand());
        if (inventoryDetails.getPrice() != null)
            inventory.setPrice(inventoryDetails.getPrice());
        if (inventoryDetails.getModel() != null)
            inventory.setModel(inventoryDetails.getModel());
        if (inventoryDetails.getKind() != null)
            inventory.setKind(inventoryDetails.getKind());

        if (inventoryDetails.getCurrentStock() != null)
            inventory.setCurrentStock(inventoryDetails.getCurrentStock());
        if (inventoryDetails.getMinimumStock() != null)
            inventory.setMinimumStock(inventoryDetails.getMinimumStock());

        return inventoryRepository.save(inventory);
    }
}
