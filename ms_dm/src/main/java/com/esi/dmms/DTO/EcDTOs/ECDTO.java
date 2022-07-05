package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECDTO {
    private Long id;
    private String createDate;

    private String taille;
    private String poid;
    private String tension;
    private String palpitation_pouls;
    private String oscultation;

    private OrdonnanceDTO ordonnance;
    private RapportDTO rapport;
    private CertificatDTO certificat;
    private EvacuationDTO evacuation;
    private OriantationDTO orientation;
}
