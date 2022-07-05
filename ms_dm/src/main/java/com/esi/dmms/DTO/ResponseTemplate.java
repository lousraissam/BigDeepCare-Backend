package com.esi.dmms.DTO;

import com.esi.dmms.DTO.EcDTOs.ECDTO;
import com.esi.dmms.DTO.PatientDTO;
import com.esi.dmms.entities.Consultation;
import com.esi.dmms.entities.DossierMedical;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {


    private PatientDTO patient;
    private DossierMedical dossierMedical;
    private Consultation consultation;


}
