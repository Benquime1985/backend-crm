package com.optica.backend_crm.controller;

import com.optica.backend_crm.models.SaleNote;
import com.optica.backend_crm.services.SaleNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales_notes")
public class SaleNoteController {

    @Autowired
    private SaleNoteService saleNoteService;

    @GetMapping("/count-by-status")
    public Map<String, Long> getCountByStatus() {
        return saleNoteService.getStatusCounts();
    }

    @GetMapping("/all")
    public List<SaleNote> saleNoteList() {
        return saleNoteService.getAllSaleNote();
    }

    @GetMapping("/{id}")
    public SaleNote getSaleNoteById(@PathVariable Integer id) {
        return saleNoteService.getSaleNoteById(id);
    }

    @GetMapping("/patient/{id}")
    public List<SaleNote> getSalesByPatient(@PathVariable Integer id) {
        return saleNoteService.getSalesByPatient(id);
    }

    @PostMapping("/create")
    public SaleNote createSaleNote(@RequestBody SaleNote saleNote) {
        return saleNoteService.saveSaleNote(saleNote);
    }

    @PutMapping("/update/{id}")
    public SaleNote updateSaleNote(@PathVariable Integer id, @RequestBody SaleNote details) {
        return saleNoteService.updateSaleNote(id, details);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSaleNote(@PathVariable Integer id) {
        saleNoteService.deleteSaleNote(id);
    }
}