package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriantationDTO {
    private Long id;

    private String createDate;

    private String patient;
    private String doctor;
    private int agePatient;
    private String cause;
    private String etatPatient;
}
