package com.optica.backend_crm.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_role;

    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

}
