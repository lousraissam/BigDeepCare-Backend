package com.esi.examenclinique.repository;

import com.esi.examenclinique.entity.Courriers.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepository extends JpaRepository<Rapport,Long> {
    Rapport findByTitre(String titre);
}
