package com.esi.bpms.services;

import com.esi.bpms.enteties.BilanLipidique;
import com.esi.bpms.enteties.BilanParaclinique;
import com.esi.bpms.repositories.BLipidiqueRepository;
import com.esi.bpms.repositories.BPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BPService {
    @Autowired
    BPRepository bpRepository;

    //post methods
    public BilanParaclinique saveBP(BilanParaclinique bp){
        return bpRepository.save(bp);
    }

    //get methods
    public List<BilanParaclinique> getBP(){
        return bpRepository.findAll();
    }

    public BilanParaclinique getBPById(Long idBP){
        return bpRepository.findById(idBP).orElse(null);
    }

    //delete method
    public String deleteBP(Long idBP){
        bpRepository.deleteById(idBP);
        return "Bilan PARACLINIQUE Removed "+idBP;
    }

    //update method
    public BilanParaclinique updateBP(BilanParaclinique bp){
        BilanParaclinique exsistingBP = bpRepository.findById(bp.getId()).orElse(null);

        exsistingBP.setTitre(bp.getTitre());
        exsistingBP.setDoctorName(bp.getDoctorName());
        exsistingBP.setPatientName(bp.getPatientName());
        exsistingBP.setPatientAge(bp.getPatientAge());


        return bpRepository.save(exsistingBP);
    }

}
