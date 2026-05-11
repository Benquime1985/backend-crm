package com.optica.backend_crm.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;




@Entity
@Data
@Table(name ="sales_notes")
public class SaleNote {


    public enum SalesStatus {
        PENDIENTE,
        LABORATORIO,
        LISTO,
        ENTREGADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sale")
    private Integer idSale;

    @Column(unique = true, length = 20)
    private String folio;

    @ManyToOne
    @JoinColumn(name="id_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_seller")
    private User seller;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="sale_date", updatable = false)
    private Timestamp date;

    private BigDecimal total;
    private BigDecimal advance;
    private BigDecimal balance;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING) // ¡ESTO ES VITAL!
    private SalesStatus status;

}
