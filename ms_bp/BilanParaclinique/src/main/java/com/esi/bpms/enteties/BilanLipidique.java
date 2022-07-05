package com.esi.bpms.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BilanLipidique extends BilanParaclinique {

    private String creatinine;
    private String triglycerides;
    private String cholesterolTotal;
    private String hdlCholesterol;
    private String ldlCholesterol;


}
