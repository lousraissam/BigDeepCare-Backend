package com.esi.examenclinique.service;

import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.entity.Courriers.Rapport;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import com.esi.examenclinique.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {
    @Autowired
    private RapportRepository rapportRepository;

    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;

    public ExamenClinique getECById(Long idEC){
        return examenCliniqueRepository.findById(idEC).orElse(null);
    }


    // POST
    public Rapport saveRapport(Rapport rapport) {
        ExamenClinique ec =  getECById(rapport.getId());
        rapport.setExamenClinique(ec);
        ec.setRapport(rapport);
        examenCliniqueRepository.save(ec);
        return rapportRepository.save(rapport);
    }

/*    public List<Rapport> saveRapports(List<Rapport> rapports) {
        return rapportRepository.saveAll(rapports);
    }*/

    // GET
    public List<Rapport> getRapports() {
        return rapportRepository.findAll();
    }

    public Rapport getRapportById(Long id) {
        return rapportRepository.findById(id).orElse(null);
    }

    public Rapport getRapportByTitre(String titre) {
        return rapportRepository.findByTitre(titre);
    }

    // PUT
    public Rapport updateRapport(Rapport rapport) {
        Rapport existingRapport = rapportRepository.findById(rapport.getId()).orElse(null);
        existingRapport.setTitre(rapport.getTitre());
        existingRapport.setZonetxt(rapport.getZonetxt());
        existingRapport.setExamenClinique(rapport.getExamenClinique());
        return rapportRepository.save(existingRapport);
    }

    // DELETE
    public String deleteRapport(Long idRap) {
        ExamenClinique ec =  getECById(idRap);
        ec.setRapport(null);
        rapportRepository.deleteById(idRap);
        return "Rapport " + idRap + " supprimé !! ";
    }

}