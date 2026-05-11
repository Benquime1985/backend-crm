package com.optica.backend_crm.services;

import com.optica.backend_crm.models.MedicalHistory;
import com.optica.backend_crm.models.Patient;
import com.optica.backend_crm.models.User;
import com.optica.backend_crm.repositories.MedicalHistoryRepository;
import com.optica.backend_crm.repositories.PatientRepository;
import com.optica.backend_crm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MedicalHistory> getAllMedicalHistory(){
        return medicalHistoryRepository.findAll();
    }

    public MedicalHistory getHistoryById(Integer id) {
        return medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado con ID: " + id));
    }

    public List<MedicalHistory> getHistoryByPatient(Integer id) {
        return medicalHistoryRepository.findByPatient_IdPatient(id);
    }

    public MedicalHistory saveMedicalHistory(MedicalHistory medicalHistory){
        return medicalHistoryRepository.save(medicalHistory);
    }

    public void  deleteMedicalHistory(Integer id){
        medicalHistoryRepository.deleteById(id);
    }

    public MedicalHistory updateHistory(Integer id, MedicalHistory historyDetails) {
        MedicalHistory history = medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        if (historyDetails.getPatient() != null) {
            Patient newPatient = patientRepository.findById(historyDetails.getPatient().getIdPatient())
                    .orElseThrow(() -> new RuntimeException("Nuevo paciente no encontrado"));
            history.setPatient(newPatient);
        }

        if (historyDetails.getOptometrist() != null) {
            User newOptometrist = userRepository.findById(historyDetails.getOptometrist().getIdUser())
                    .orElseThrow(() -> new RuntimeException("Optometrista no encontrado"));
            history.setOptometrist(newOptometrist);
        }

        if (historyDetails.getAvOd() != null) history.setAvOd(historyDetails.getAvOd());
        if (historyDetails.getAvOi() != null) history.setAvOi(historyDetails.getAvOi());
        if (historyDetails.getPinhole() != null) history.setPinhole(historyDetails.getPinhole());
        if (historyDetails.getKOd() != null) history.setKOd(historyDetails.getKOd());
        if (historyDetails.getKOi() != null) history.setKOi(historyDetails.getKOi());

        if (historyDetails.getOdSphere() != null) history.setOdSphere(historyDetails.getOdSphere());
        if (historyDetails.getOdCylinder() != null) history.setOdCylinder(historyDetails.getOdCylinder());
        if (historyDetails.getOdAxis() != null) history.setOdAxis(historyDetails.getOdAxis());
        if (historyDetails.getOiSphere() != null) history.setOiSphere(historyDetails.getOiSphere());
        if (historyDetails.getOiCylinder() != null) history.setOiCylinder(historyDetails.getOiCylinder());
        if (historyDetails.getOiAxis() != null) history.setOiAxis(historyDetails.getOiAxis());

        if (historyDetails.getBifocal() != null) history.setBifocal(historyDetails.getBifocal());
        if (historyDetails.getAdicionAdd() != null) history.setAdicionAdd(historyDetails.getAdicionAdd());
        if (historyDetails.getAWafer() != null) history.setAWafer(historyDetails.getAWafer());
        if (historyDetails.getLensColor() != null) history.setLensColor(historyDetails.getLensColor());
        if (historyDetails.getInterpupillaryDistance() != null)
            history.setInterpupillaryDistance(historyDetails.getInterpupillaryDistance());

        if (historyDetails.getObservations() != null) history.setObservations(historyDetails.getObservations());

        return medicalHistoryRepository.save(history);
    }
}
