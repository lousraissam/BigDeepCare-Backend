package com.esi.bpms.repositories;

import com.esi.bpms.enteties.BilanLipidique;
import com.esi.bpms.enteties.BilanParaclinique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BPRepository extends JpaRepository<BilanParaclinique, Long> {
}
