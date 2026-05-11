package com.optica.backend_crm.repositories;

import com.optica.backend_crm.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.currentStock <= i.minimumStock")
    List<Inventory> findLowStockProducts();

    List<Inventory> findByKindAndCurrentStockLessThanEqual(String kind, Integer min);

}
