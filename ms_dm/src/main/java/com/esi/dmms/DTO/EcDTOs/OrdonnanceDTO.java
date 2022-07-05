package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdonnanceDTO {
    private Long id;

    private String createDate;
    private MedicamentDTO medicaments;

    //Autre medications
    private String autre;
}
