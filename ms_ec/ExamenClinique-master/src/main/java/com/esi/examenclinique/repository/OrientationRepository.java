package com.esi.examenclinique.repository;

import com.esi.examenclinique.entity.Courriers.Orientation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrientationRepository extends JpaRepository<Orientation,Long> {
}
