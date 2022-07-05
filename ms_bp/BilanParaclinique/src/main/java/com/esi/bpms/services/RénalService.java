package com.esi.bpms.services;

import com.esi.bpms.enteties.Rénal;
import com.esi.bpms.repositories.RénalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RénalService {
    @Autowired
    private RénalRepository rénalRepository;

    // POST
    public Rénal saveRénal(Rénal rénal) {
        return rénalRepository.save(rénal);
    }

    // GET
    public List<Rénal> getRénals() {
        return rénalRepository.findAll();
    }

    public Rénal getRénalById(Long id) {
        return rénalRepository.findById(id).orElse(null);
    }

    // PUT
    public Rénal updateRénal(Rénal rénal) {
        Rénal existingRénal = rénalRepository.findById(rénal.getId()).orElse(null);
        existingRénal.setPH(rénal.getPH());
        existingRénal.setDensite(rénal.getDensite());
        existingRénal.setGlucose(rénal.getGlucose());
        existingRénal.setCorps_cetoniques(rénal.getCorps_cetoniques());
        existingRénal.setProteines(rénal.getProteines());
        existingRénal.setSang(rénal.getSang());
        existingRénal.setLeucocytes(rénal.getLeucocytes());
        existingRénal.setNitrites(rénal.getNitrites());
        existingRénal.setUrobilinogene(rénal.getUrobilinogene());
        existingRénal.setBilirubine(rénal.getBilirubine());
        return rénalRepository.save(existingRénal);
    }

    // DELETE
    public String deleteRénal(Long idRénal) {
        rénalRepository.deleteById(idRénal);
        return "Bilan [ Rénal ] " + idRénal + " supprimé !! ";
    }
}
