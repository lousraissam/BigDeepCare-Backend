package com.esi.dmms.controller;

import com.esi.dmms.entities.Antecedent;
import com.esi.dmms.entities.DossierMedical;
import com.esi.dmms.service.AntecedentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AntecedentController {
    @Autowired
    AntecedentService antecedentService;

    @PostMapping("{id}/addAnt")
    public DossierMedical addAnt(@PathVariable(name = "id") Long idDM, @RequestBody Antecedent ant){
        return antecedentService.saveAnt(idDM,ant);
    }

    @GetMapping("/Ants")
    public List<Antecedent> getAFs(){
        return antecedentService.getAnt();
    }
    @GetMapping("/Ant/{id}")
    public Antecedent getAntById(@PathVariable(name = "id") Long idAnt){
        return antecedentService.getAntById(idAnt);
    }

    @PutMapping("/updateAnt")
    public Antecedent updateAnt(@RequestBody Antecedent ant){

        return antecedentService.updateAnt(ant);
    }

    @DeleteMapping("/deleteAnt/{id}")
    public void deleteAnt(@PathVariable(name = "id") Long idAnt){
        antecedentService.deleteAnt(idAnt);
    }
}
