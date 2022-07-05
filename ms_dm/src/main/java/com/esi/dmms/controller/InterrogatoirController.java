package com.esi.dmms.controller;

import com.esi.dmms.entities.Consultation;
import com.esi.dmms.entities.Interrogatoir;
import com.esi.dmms.service.InterrogatoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Consultation")
public class InterrogatoirController {

    @Autowired
    private InterrogatoirService interrogatoirService;


    @PostMapping("{id}/addIntero")
    public Consultation addInterro(@PathVariable(name = "id") Long idC, @RequestBody Interrogatoir interrogatoir){
        return interrogatoirService.saveInterrogatoir(idC, interrogatoir);
    }

    @GetMapping("/Interros")
    public List<Interrogatoir> getInterros(){
        return interrogatoirService.getInterro();
    }

    @GetMapping("/Interro/{id}")
    public Interrogatoir getInterroById(@PathVariable(name = "id") Long idInterro){
        return interrogatoirService.getInterroById(idInterro);
    }

    @PutMapping("/updateInterro")
    public Interrogatoir updateInterro(@RequestBody Interrogatoir interrogatoir){
        return interrogatoirService.updateInterro(interrogatoir);
    }

    @DeleteMapping("/deleteInterro/{id}")
    public void deleteInterro(@PathVariable(name = "id") Long idInterro){
        interrogatoirService.deleteInterro(idInterro);
    }
}

