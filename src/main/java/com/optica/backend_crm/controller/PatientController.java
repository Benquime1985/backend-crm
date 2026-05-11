package com.optica.backend_crm.controller;


import com.optica.backend_crm.models.Patient;
import com.optica.backend_crm.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public List<Patient> patientList(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Integer id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam("term") String term) {
        return patientService.searchPatients(term);
    }

    @PostMapping("/create")
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@RequestBody Patient patientDetails, @PathVariable Integer id){
        return  patientService.updatePatient(id, patientDetails);
    }
}
