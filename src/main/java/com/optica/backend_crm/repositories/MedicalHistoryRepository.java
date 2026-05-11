package com.optica.backend_crm.repositories;

import com.optica.backend_crm.models.MedicalHistory;
import com.optica.backend_crm.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {
    List<MedicalHistory> findByPatient_IdPatient(Integer id);
}
