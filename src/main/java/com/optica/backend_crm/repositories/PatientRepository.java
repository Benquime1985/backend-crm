package com.optica.backend_crm.repositories;

import com.optica.backend_crm.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    List<Patient> findByNamepatientContainingIgnoreCaseOrPhoneContaining(String name, String phone);
}
