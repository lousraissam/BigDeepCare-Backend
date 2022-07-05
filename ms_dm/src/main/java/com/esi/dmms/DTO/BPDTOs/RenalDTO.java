package com.esi.dmms.DTO.BPDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenalDTO extends BPDTO{

    private String pH;
    private String densite;
    private String glucose;
    private String corps_cetoniques;
    private String proteines;
    private String sang;
    private String leucocytes;
    private String nitrites;
    private String urobilinogene;
    private String bilirubine;
}
