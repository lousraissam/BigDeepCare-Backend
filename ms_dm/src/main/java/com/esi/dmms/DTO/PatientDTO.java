package com.esi.dmms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Long id;

    private String CIN;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private int age;
    private String bloodType;
    private String sexe;
    private String adresse;
    private String telephone;
    private String deviceKey;

}
