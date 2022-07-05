package com.esi.dmms.service;

import com.esi.dmms.entities.Consultation;
import com.esi.dmms.entities.Interrogatoir;
import com.esi.dmms.repository.ConsultationRepository;
import com.esi.dmms.repository.InterrogatoirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterrogatoirService {

    @Autowired
    InterrogatoirRepository interrogatoirRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    //post methods
    public Consultation saveInterrogatoir(Long idC, Interrogatoir interrogatoir){
        Consultation c = consultationRepository.findById(idC).orElse(null);
        c.setInterrogatoir(interrogatoir);

        interrogatoir.setConsultation(c);
        return consultationRepository.save(c);
    }

    //get methods
    public List<Interrogatoir> getInterro(){
        return interrogatoirRepository.findAll();
    }

    public Interrogatoir getInterroById(Long idInterro){
        return interrogatoirRepository.findById(idInterro).orElse(null);
    }

    //delete method
    public void deleteInterro(Long idInterro){
        interrogatoirRepository.deleteById(idInterro);
    }

    //update method
    public Interrogatoir updateInterro(Interrogatoir interro){
        Interrogatoir exsistingInterro= interrogatoirRepository.findById(interro.getId()).orElse(null);
        exsistingInterro.setColesterol(interro.getColesterol());
        exsistingInterro.setDyspne(interro.getDyspne());
        exsistingInterro.setHypotimie(interro.getHypotimie());
        exsistingInterro.setDouleur_torasique(interro.getDouleur_torasique());
        exsistingInterro.setPalpitations(interro.getPalpitations());
        exsistingInterro.setSedantarite(interro.getSedantarite());
        exsistingInterro.setSyncope(interro.getSyncope());
        exsistingInterro.setConsultation(interro.getConsultation());

        return interrogatoirRepository.save(exsistingInterro);
    }
}
