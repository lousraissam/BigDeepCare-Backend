package com.esi.examenclinique.controller;


import com.esi.examenclinique.entity.Courriers.Rapport;
import com.esi.examenclinique.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportController {
    @Autowired
    private RapportService rapportService;

    // POST
    @PostMapping("/addRapport")
    public Rapport addRapport(@RequestBody Rapport rapport) {
        return rapportService.saveRapport(rapport);
    }

/*    @PostMapping("/addRapports")
    public List<Rapport> addRapports(@RequestBody List<Rapport> rapports) {
        return rapportService.saveRapports(rapports);
    }*/

    // GET
    @GetMapping("/rapports")
    public List<Rapport> findAllRapports() {
        return rapportService.getRapports();
    }

    @GetMapping("/rapportById/{id}")
    public Rapport findRapportById(@PathVariable Long id) {
        return rapportService.getRapportById(id);
    }

    @GetMapping("/rapport/{titre}")
    public Rapport findRapportByTitre(@PathVariable String titre) {
        return rapportService.getRapportByTitre(titre);
    }

    // PUT
    @PutMapping("/updateRap")
    public Rapport updateRapport(@RequestBody Rapport rapport) {
        return rapportService.updateRapport(rapport);
    }

    // DELETE
    @DeleteMapping("/deleteRap/{id}")
    public String deleteRapport(@PathVariable Long id) {
        return rapportService.deleteRapport(id);
    }

}

