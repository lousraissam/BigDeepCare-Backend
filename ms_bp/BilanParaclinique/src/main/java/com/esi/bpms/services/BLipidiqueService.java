package com.esi.bpms.services;

import com.esi.bpms.enteties.BilanLipidique;
import com.esi.bpms.repositories.BLipidiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BLipidiqueService {
    @Autowired
    BLipidiqueRepository bLipidiqueRepository;

    //post methods
    public BilanLipidique saveBL(BilanLipidique bl){
        return bLipidiqueRepository.save(bl);
    }

    //get methods
    public List<BilanLipidique> getBL(){
        return bLipidiqueRepository.findAll();
    }

    public BilanLipidique getBLById(Long idBL){
        return bLipidiqueRepository.findById(idBL).orElse(null);
    }

    //delete method
    public String deleteBL(Long idBL){
        bLipidiqueRepository.deleteById(idBL);
        return "Bilan Lipidique Removed "+idBL;
    }

    //update method
    public BilanLipidique updateBL(BilanLipidique bl){
        BilanLipidique exsistingBL = bLipidiqueRepository.findById(bl.getId()).orElse(null);
        exsistingBL.setCreatinine(bl.getCreatinine());
        exsistingBL.setTriglycerides(bl.getTriglycerides());
        exsistingBL.setCholesterolTotal(bl.getCholesterolTotal());
        exsistingBL.setHdlCholesterol(bl.getHdlCholesterol());
        exsistingBL.setLdlCholesterol(bl.getLdlCholesterol());

        exsistingBL.setTitre(bl.getTitre());
        exsistingBL.setDoctorName(bl.getDoctorName());
        exsistingBL.setPatientName(bl.getPatientName());
        exsistingBL.setPatientAge(bl.getPatientAge());

        return bLipidiqueRepository.save(exsistingBL);
    }

}
