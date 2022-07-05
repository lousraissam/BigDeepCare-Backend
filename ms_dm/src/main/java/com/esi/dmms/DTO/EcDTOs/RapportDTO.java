package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RapportDTO {
    private Long id;
    private String createDate;
    private String doctor;
    private String patient;
    private String patientBD; // date of birth Patient
    private String cause;
    private String recommandation;

}
