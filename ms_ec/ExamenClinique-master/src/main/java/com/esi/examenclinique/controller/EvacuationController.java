package com.esi.examenclinique.controller;

import com.esi.examenclinique.entity.Courriers.Evacuation;
import com.esi.examenclinique.service.EvacuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvacuationController {

    @Autowired
    private EvacuationService evacuationService;

    // POST
    @PostMapping("/addEvacuation")
    public Evacuation addOrientation(@RequestBody Evacuation evacuation) {
        return evacuationService.saveEvacuation(evacuation);
    }

/*    @PostMapping("/addEvacuations")
    public List<Evacuation> addEvacuations(@RequestBody List<Evacuation> evacuations) {
        return evacuationService.saveEvacuations(evacuations);
    }*/

    // GET
    @GetMapping("/evacuations")
    public List<Evacuation> findAllEvacuations() {
        return evacuationService.getEvacuations();
    }

    @GetMapping("/evacuationById/{id}")
    public Evacuation findEvacuationById(@PathVariable Long id) {
        return evacuationService.getEvacuationById(id);
    }

    @GetMapping("/evacuation/{objet}")
    public Evacuation findEvacuationByObjet(@PathVariable String objet) {
        return evacuationService.getEvacuationByObjet(objet);
    }

    // PUT
    @PutMapping("/updateEvac")
    public Evacuation updateEvacuation(@RequestBody Evacuation evacuation) {
        return evacuationService.updateEvacuation(evacuation);
    }

    // DELETE
    @DeleteMapping("/deleteEvac/{id}")
    public String deleteEvacuation(@PathVariable Long id) {
        return evacuationService.deleteEvacuation(id);
    }

}
