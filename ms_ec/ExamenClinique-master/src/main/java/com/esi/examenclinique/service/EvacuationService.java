package com.esi.examenclinique.service;

import com.esi.examenclinique.entity.Courriers.Evacuation;
import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.repository.EvacuationRepository;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvacuationService {
    @Autowired
    private EvacuationRepository evacuationRepository;

    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;

    public ExamenClinique getECById(Long idEC){
        return examenCliniqueRepository.findById(idEC).orElse(null);
    }


    // POST
    public Evacuation saveEvacuation(Evacuation evacuation) {
        ExamenClinique ec =  getECById(evacuation.getId());
        evacuation.setExamenClinique(ec);
        ec.setEvacuation(evacuation);
        examenCliniqueRepository.save(ec);
        return evacuationRepository.save(evacuation);
    }

/*    public List<Evacuation> saveEvacuations(List<Evacuation> evacuations) {
        return evacuationRepository.saveAll(evacuations);
    }*/

    // GET
    public List<Evacuation> getEvacuations() {
        return evacuationRepository.findAll();
    }

    public Evacuation getEvacuationById(Long id) {
        return evacuationRepository.findById(id).orElse(null);
    }

    public Evacuation getEvacuationByObjet(String objet) {
        return evacuationRepository.findByObjet(objet);
    }

    // PUT
    public Evacuation updateEvacuation(Evacuation evacuation) {
        Evacuation existingEvacuation = evacuationRepository.findById(evacuation.getId()).orElse(null);
        existingEvacuation.setObjet(evacuation.getObjet());
        existingEvacuation.setZonetxt(evacuation.getZonetxt());
        existingEvacuation.setExamenClinique(evacuation.getExamenClinique());
        return evacuationRepository.save(existingEvacuation);
    }

    // DELETE
    public String deleteEvacuation(Long idEvac) {
        ExamenClinique ec =  getECById(idEvac);
        ec.setEvacuation(null);
        evacuationRepository.deleteById(idEvac);
        return "Evacuation " + idEvac + " supprimé !! ";
    }

}
