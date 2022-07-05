package com.esi.dmms.DTO.EcDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentDTO {
    private Long id;
    private String nom;
    private int posologie;
    private int quantite;
}
