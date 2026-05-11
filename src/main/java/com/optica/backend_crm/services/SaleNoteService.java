package com.optica.backend_crm.services;


import com.optica.backend_crm.models.Patient;
import com.optica.backend_crm.models.SaleNote;
import com.optica.backend_crm.models.User;
import com.optica.backend_crm.repositories.PatientRepository;
import com.optica.backend_crm.repositories.SaleNoteRepository;
import com.optica.backend_crm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleNoteService {

    @Autowired
    private SaleNoteRepository saleNoteRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SaleNote> getAllSaleNote(){
        return saleNoteRepository.findAll();
    }

    public Map<String, Long> getStatusCounts() {
        // 1. Obtenemos los datos reales de la DB
        Map<String, Long> countsFromDb = saleNoteRepository.getCountByStatusMap();

        // 2. Creamos un mapa con todos los estados en 0 por defecto
        Map<String, Long> finalCounts = new HashMap<>();
        for (SaleNote.SalesStatus status : SaleNote.SalesStatus.values()) {
            finalCounts.put(status.name(), 0L);
        }

        // 3. Mezclamos los datos reales
        finalCounts.putAll(countsFromDb);

        return finalCounts;
    }

    public SaleNote getSaleNoteById(Integer id) {
        return saleNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrado con ID: " + id));
    }

    public List<SaleNote> getSalesByPatient(Integer id) {
        return  saleNoteRepository.findByPatient_IdPatient(id);
    }

    public SaleNote saveSaleNote(SaleNote saleNote) {
        if (saleNote.getTotal() != null && saleNote.getAdvance() != null) {
            saleNote.setBalance(saleNote.getTotal().subtract(saleNote.getAdvance()));
        }
        return saleNoteRepository.save(saleNote);
    }

    public void deleteSaleNote(Integer id){
        saleNoteRepository.deleteById(id);
    }


    public SaleNote updateSaleNote(Integer id, SaleNote details) {
        SaleNote sale = saleNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de venta no encontrada con ID: " + id));

        if (details.getPatient() != null) {
            Patient p = patientRepository.findById(details.getPatient().getIdPatient())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            sale.setPatient(p);
        }

        if (details.getSeller() != null) {
            User u = userRepository.findById(details.getSeller().getIdUser())
                    .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
            sale.setSeller(u);
        }

        if (details.getFolio() != null) sale.setFolio(details.getFolio());
        if (details.getDeliveryDate() != null) sale.setDeliveryDate(details.getDeliveryDate());
        if (details.getStatus() != null) sale.setStatus(details.getStatus());
        if (details.getTotal() != null) sale.setTotal(details.getTotal());
        if (details.getAdvance() != null) sale.setAdvance(details.getAdvance());

        if (sale.getTotal() != null && sale.getAdvance() != null) {
            sale.setBalance(sale.getTotal().subtract(sale.getAdvance()));
        }

        return saleNoteRepository.save(sale);
    }
}
