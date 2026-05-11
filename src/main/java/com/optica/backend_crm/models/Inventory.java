package com.optica.backend_crm.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    private String sku;
    private String brand;
    private String model;
    private String kind;

    private BigDecimal price;

    @Column(name = "current_stock")
    private Integer currentStock = 0;

    @Column(name = "minimum_stock")
    private Integer minimumStock = 5;

}
