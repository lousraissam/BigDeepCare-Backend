package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificatDTO {
    private Long id;

    private String createDate;

    private String doctor;
    private String patient;
    private String patientBD; // date of birth Patient
    private String cause;
    private String recommandation;
}
