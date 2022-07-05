package com.esi.examenclinique.controller;

import com.esi.examenclinique.entity.Courriers.Medicament;
import com.esi.examenclinique.entity.Courriers.Ordonnance;
import com.esi.examenclinique.service.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdonnanceController {
    @Autowired
    private OrdonnanceService ordonnanceService;

    @PostMapping("{idec}/addOrd")
    public Ordonnance addOrdonnance(@PathVariable Long idec, @RequestBody Ordonnance ordonnance){
        return ordonnanceService.saveOrdonnance(idec, ordonnance);
    }
    @PostMapping("{idec}/addDoses")
    public Medicament addMedicaments(@PathVariable Long idec, @RequestBody Medicament medicaments){
        return ordonnanceService.saveMedicaments(idec, medicaments);
    }


    @GetMapping("/Ords")
    public List<Ordonnance> getOrdonnances(){
        return ordonnanceService.getOrdonnances();
    }
    @GetMapping("/Ord/{id}")
    public Ordonnance getOrdonnanceById(@PathVariable(name = "id") Long idOrd){
        return ordonnanceService.getOrdonnanceById(idOrd);
    }

    @PutMapping("/updateOrd")
    public Ordonnance updateOrdonnance(@RequestBody Ordonnance ordonnance){

        return ordonnanceService.updateOrdonnance(ordonnance);
    }

    @DeleteMapping("/deleteOrd/{id}")
    public String deleteOrdonnance(@PathVariable(name = "id") Long idOrd){
        return ordonnanceService.deleteOrdonnance(idOrd);
    }
}
