package com.esi.dmms.DTO.BPDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BPDTO {

    private Long id;
    private String createDate;
    private String titre;
    private String doctorName;
    private String patientName;
    private int patientAge;
    private String date;

    //hbA1c
    private String glycemie_a_jeun;
    private String hemoglobine_glyquee;
    // lipidique
    private String creatinine;
    private String triglycerides;
    private String cholesterolTotal;
    private String hdlCholesterol;
    private String ldlCholesterol;
    //renal
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
