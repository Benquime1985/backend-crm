package com.optica.backend_crm.controller;

import com.optica.backend_crm.models.MedicalHistory;
import com.optica.backend_crm.services.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical_history")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("/all")
    public List<MedicalHistory> medicalHistoryList(){
        return medicalHistoryService.getAllMedicalHistory();
    }

    @GetMapping("/{id}")
    public MedicalHistory getMedicalHistoryById(@PathVariable Integer id) {
        return medicalHistoryService.getHistoryById(id);
    }

    @GetMapping("/patient/{id}")
    public List<MedicalHistory> getByPatient(@PathVariable Integer id) {
        return medicalHistoryService.getHistoryByPatient(id);
    }

    @PostMapping("/create")
    public MedicalHistory createMedicalHistory(@RequestBody MedicalHistory medicalHistory){
        return medicalHistoryService.saveMedicalHistory(medicalHistory);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMedicalHistory(@PathVariable Integer id){
        medicalHistoryService.deleteMedicalHistory(id);
    }

    @PutMapping("/update/{id}")
    public MedicalHistory updateMedicalHistory(@RequestBody MedicalHistory historyDetails, @PathVariable Integer id){
        return medicalHistoryService.updateHistory(id, historyDetails);
    }

}
