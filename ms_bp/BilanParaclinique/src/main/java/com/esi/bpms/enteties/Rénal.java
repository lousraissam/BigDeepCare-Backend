package com.esi.bpms.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "RÉNAL_TBL")
public class Rénal extends BilanParaclinique {

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
    //private LeucocytesEnum leucocytes;


}
