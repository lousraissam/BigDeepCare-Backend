package com.esi.examenclinique.repository;

import com.esi.examenclinique.entity.ExamenClinique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenCliniqueRepository extends JpaRepository<ExamenClinique, Long> {
}
