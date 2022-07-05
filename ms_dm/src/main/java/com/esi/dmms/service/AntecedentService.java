package com.esi.dmms.service;

import com.esi.dmms.entities.Antecedent;
import com.esi.dmms.entities.DossierMedical;
import com.esi.dmms.repository.AntecedentRepository;
import com.esi.dmms.repository.DossierMedicalRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedentService {

    @Autowired
    AntecedentRepository antecedentRepository;

    @Autowired
    DossierMedicalRepostory dossierMedicalRepostory;

    //post methods
    public DossierMedical saveAnt(Long idDM, Antecedent ant){
        DossierMedical dm = dossierMedicalRepostory.findById(idDM).orElse(null);
        dm.getAntecedents().add(ant);

        ant.setDossierMedical(dm);
        return dossierMedicalRepostory.save(dm);
    }

    //get methods
    public List<Antecedent> getAnt(){
        return antecedentRepository.findAll();
    }

    public Antecedent getAntById(Long idAnt){
        return antecedentRepository.findById(idAnt).orElse(null);
    }

    //delete method
    public void deleteAnt(Long idAnt){
        antecedentRepository.deleteById(idAnt);
    }

    //update method
    public Antecedent updateAnt(Antecedent ant){
        Antecedent exsistingAnt= antecedentRepository.findById(ant.getId()).orElse(null);
        exsistingAnt.setNom(ant.getNom());
        exsistingAnt.setType(ant.getType());
        exsistingAnt.setRemarque(ant.getRemarque());
        exsistingAnt.setType(ant.getType());
        exsistingAnt.setDossierMedical(ant.getDossierMedical());

        return antecedentRepository.save(exsistingAnt);
    }
}
