package com.esi.examenclinique.repository;

import com.esi.examenclinique.entity.Courriers.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
}
