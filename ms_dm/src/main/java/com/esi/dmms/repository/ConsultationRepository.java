package com.esi.dmms.repository;

import com.esi.dmms.entities.Consultation;
import com.esi.dmms.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    public List<Consultation> findConsultationByDm(DossierMedical dm);
}
