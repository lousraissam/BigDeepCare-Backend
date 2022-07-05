package com.esi.examenclinique.service;

import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.entity.Courriers.Orientation;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import com.esi.examenclinique.repository.OrientationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrientationService {
    @Autowired
    private OrientationRepository orientationRepository;

    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;

    public ExamenClinique getECById(Long idEC){
        return examenCliniqueRepository.findById(idEC).orElse(null);
    }

    // POST
    public Orientation saveOrientation(Long idec, Orientation orientation) {
        ExamenClinique ec =  examenCliniqueRepository.findById(idec).orElse(null);
        ec.setOrientation(orientation);
        orientation.setExamenClinique(ec);
        return orientationRepository.save(orientation);
    }

    // GET
    public List<Orientation> getOrientations() {
        return orientationRepository.findAll();
    }

    public Orientation getOrientationById(Long id) {
        return orientationRepository.findById(id).orElse(null);
    }


    // PUT
    public Orientation updateOrientation(Orientation orientation) {
        Orientation existingOrientation = orientationRepository.findById(orientation.getId()).orElse(null);
        existingOrientation.setEtatPatient(orientation.getEtatPatient());
        existingOrientation.setPatient(orientation.getPatient());
        existingOrientation.setDoctor(orientation.getDoctor());
        existingOrientation.setCause(orientation.getCause());
        existingOrientation.setAgePatient(orientation.getAgePatient());
        existingOrientation.setExamenClinique(orientation.getExamenClinique());
        return orientationRepository.save(existingOrientation);
    }

    // DELETE
    public String deleteOrientation(Long idOrien) {
        ExamenClinique ec =  getECById(idOrien);
        ec.setOrientation(null);
        orientationRepository.deleteById(idOrien);
        return "Orientation " + idOrien + " supprimé !! ";
    }

}
