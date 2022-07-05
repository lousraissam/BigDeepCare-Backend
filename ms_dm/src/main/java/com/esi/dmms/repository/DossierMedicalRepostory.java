package com.esi.dmms.repository;

import com.esi.dmms.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DossierMedicalRepostory extends JpaRepository<DossierMedical,Long> {
    public DossierMedical findDossierMedicalByPatientId(Long idp);
}
