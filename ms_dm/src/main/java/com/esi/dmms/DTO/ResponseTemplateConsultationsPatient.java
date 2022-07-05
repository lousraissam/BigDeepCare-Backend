package com.esi.dmms.DTO;

import com.esi.dmms.entities.Consultation;
import com.esi.dmms.entities.DossierMedical;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateConsultationsPatient {


    private PatientDTO patient;
    private Consultation consultation;


}
