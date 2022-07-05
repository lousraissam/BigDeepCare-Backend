package com.esi.bpms.controllers;


import com.esi.bpms.enteties.BilanLipidique;
import com.esi.bpms.services.BLipidiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BLipidiqueContoller {
    @Autowired
    BLipidiqueService bLipidiqueService;

    @PostMapping("/addBL")
    public BilanLipidique addBL(@RequestBody BilanLipidique bl){
        return bLipidiqueService.saveBL(bl);
    }

    @GetMapping("/BLs")
    public List<BilanLipidique> getBls(){
        return bLipidiqueService.getBL();
    }
    @GetMapping("/BL/{id}")
    public BilanLipidique getExamenMedicalById(@PathVariable(name = "id") Long idBL){
        return bLipidiqueService.getBLById(idBL);
    }

    @PutMapping("/updateBL")
    public BilanLipidique updateBL(@RequestBody BilanLipidique bl){

        return bLipidiqueService.updateBL(bl);
    }

    @DeleteMapping("/deleteBL/{id}")
    public String deleteBL(@PathVariable(name = "id") Long idBL){
        return bLipidiqueService.deleteBL(idBL);
    }
}
