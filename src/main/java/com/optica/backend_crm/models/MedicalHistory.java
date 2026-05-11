package com.optica.backend_crm.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Integer idHistory;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_optometrist")
    private User optometrist;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "exam_date", updatable = false)
    private LocalDate examDate;

    @Column(name = "av_od")
    private String avOd;
    @Column(name = "av_oi")
    private String avOi;
    private String pinhole;
    @Column(name = "k_od")
    private String kOd;
    @Column(name = "k_oi")
    private String kOi;

    @Column(name = "od_sphere")
    private BigDecimal odSphere;
    @Column(name = "od_cylinder")
    private BigDecimal odCylinder;
    @Column(name = "od_axis")
    private Integer odAxis;
    @Column(name = "oi_sphere")
    private BigDecimal oiSphere;
    @Column(name = "oi_cylinder")
    private BigDecimal oiCylinder;
    @Column(name = "oi_axis")
    private Integer oiAxis;

    // Detalles
    private String bifocal;
    @Column(name = "adicion_add")
    private BigDecimal adicionAdd;
    @Column(name = "a_wafer")
    private String aWafer;
    @Column(name = "lens_color")
    private String lensColor;
    @Column(name = "interpupillary_distance")
    private String interpupillaryDistance;

    private  String observations;

}
