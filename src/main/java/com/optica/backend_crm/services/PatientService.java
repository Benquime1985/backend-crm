package com.optica.backend_crm.services;


import com.optica.backend_crm.models.Patient;
import com.optica.backend_crm.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository  patientRepository;


    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    public List<Patient> searchPatients(String query) {
        return patientRepository.findByNamepatientContainingIgnoreCaseOrPhoneContaining(query, query);
    }

    public Patient savePatient (Patient patient){
        return patientRepository.save(patient);
    }

    public void deletePatient (Integer id){
        patientRepository.deleteById(id);
    }

    public Patient updatePatient (Integer id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado con id: " + id));

        patient.setNamepatient(patientDetails.getNamepatient());
        patient.setAge(patientDetails.getAge());
        patient.setAddress(patientDetails.getAddress());
        patient.setPhone(patientDetails.getPhone());
        patient.setBackground(patientDetails.getBackground());
        patient.setOccupation(patientDetails.getOccupation());

        return patientRepository.save(patient);
    }
}
