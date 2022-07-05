package com.esi.bpms.services;

import com.esi.bpms.enteties.HbA1c;
import com.esi.bpms.repositories.HbA1cRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HbA1cService {
    @Autowired
    private HbA1cRepository hbA1cRepository;

    // POST
    public HbA1c saveHbA1c(HbA1c hbA1c) {
        return hbA1cRepository.save(hbA1c);
    }

    // GET
    public List<HbA1c> getHbA1cs() {
        return hbA1cRepository.findAll();
    }

    public HbA1c getHbA1cById(Long id) {
        return hbA1cRepository.findById(id).orElse(null);
    }

    // PUT
    public HbA1c updateHbA1c(HbA1c hbA1c) {
        HbA1c existingHbA1c = hbA1cRepository.findById(hbA1c.getId()).orElse(null);
        existingHbA1c.setGlycemie_a_jeun(hbA1c.getGlycemie_a_jeun());
        existingHbA1c.setHemoglobine_glyquee(hbA1c.getHemoglobine_glyquee());
        return hbA1cRepository.save(existingHbA1c);
    }

    // DELETE
    public String deleteHbA1c(Long idHbA1c) {
        hbA1cRepository.deleteById(idHbA1c);
        return "Bilan [ HbA1c ] " + idHbA1c + " supprimé !! ";
    }

}
