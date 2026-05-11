package com.optica.backend_crm.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data

@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Integer idPatient;

    @Column(name="name_complete", nullable = false)
    private String namepatient;

    private String address;
    private String phone;
    private String occupation;
    private Integer age;
    private String background;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="registration_date", updatable = false)
    private Timestamp date;
}
