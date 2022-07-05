package com.esi.dmms.service;

import com.esi.dmms.DTO.PatientDTO;
import com.esi.dmms.DTO.ResponseTemplate;
import com.esi.dmms.entities.Antecedent;
import com.esi.dmms.entities.DossierMedical;
import com.esi.dmms.repository.AntecedentRepository;
import com.esi.dmms.repository.DossierMedicalRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DossierMedicalService {

    @Autowired
    private DossierMedicalRepostory dossierMedicalRepostory;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AntecedentRepository antecedentRepository;

    //post methods
    public DossierMedical saveDossierMedical(DossierMedical dossierMedical){
        return dossierMedicalRepostory.save(dossierMedical);
    }

    public List<DossierMedical> saveDossiersMedicaux(List<DossierMedical> dossiersMedicaux){
        return dossierMedicalRepostory.saveAll(dossiersMedicaux);
    }

    //get methods
    public List<DossierMedical> getDossiersMedicaux(){
        return dossierMedicalRepostory.findAll();
    }

    public DossierMedical getDossierMedicalById(Long idDM){
        return dossierMedicalRepostory.findById(idDM).orElse(null);
    }







    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }
    public ResponseTemplate getDossierMedicalWithPatient(Long idDM){
        ResponseTemplate rt = new ResponseTemplate();
        DossierMedical dm = dossierMedicalRepostory.findById(idDM).orElse(null);

        String token = getBearerTokenHeader();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);

        // use Rest template to consume rest api
        // use headers to generate jwtEntity
        //return response in PatientDTO format

        ResponseEntity<PatientDTO> patientResponse =  restTemplate.exchange(
                "http://localhost:9001/users/patient/"+dm.getPatientId(), HttpMethod.GET, jwtEntity,
                PatientDTO.class);

        PatientDTO patient = patientResponse.getBody();

        rt.setDossierMedical(dm);
        rt.setPatient(patient);
        return rt;
    }
    public PatientDTO getPatientByDMId(Long idDM){

        DossierMedical dm = dossierMedicalRepostory.findById(idDM).orElse(null);
        String token = getBearerTokenHeader();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);

        ResponseEntity<PatientDTO> patientResponse =  restTemplate.exchange(
                "http://localhost:8080/users/patient/"+dm.getPatientId(), HttpMethod.GET, jwtEntity,
                PatientDTO.class);

        PatientDTO patient = patientResponse.getBody();
        return patient;
    }

    //delete method
    public String deleteDossierMedical(Long idDM){
        dossierMedicalRepostory.deleteById(idDM);
        return "Dossier Medical Removed "+idDM;
    }

    //update method
    public DossierMedical updateDossierMedical(DossierMedical dossierMedical){
        DossierMedical exsistingDM = dossierMedicalRepostory.findById(dossierMedical.getId()).orElse(null);
        exsistingDM.setDescription(dossierMedical.getDescription());
        exsistingDM.setPatientId(dossierMedical.getPatientId());
        return dossierMedicalRepostory.save(exsistingDM);
    }

    public DossierMedical updateDMAntecedants(Collection<Antecedent> a, Long idp){
        DossierMedical exsistingDM = dossierMedicalRepostory.findDossierMedicalByPatientId(idp);
        if(a!= null) {

            exsistingDM.getAntecedents().clear();
            exsistingDM.getAntecedents().addAll(a);
            for (Antecedent aa :a) {
                aa.setDossierMedical(exsistingDM);
            }
        }
        return dossierMedicalRepostory.save(exsistingDM);
    }

    public DossierMedical getDossierMedicalByPid(Long idp) {
        return dossierMedicalRepostory.findDossierMedicalByPatientId(idp);
    }

    public List<PatientDTO> getAllPatients(){

        String token = getBearerTokenHeader();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);

        ResponseEntity<List<PatientDTO>> patientResponse =  restTemplate.exchange(
                "http://localhost:8080/aideS/patients", HttpMethod.GET, jwtEntity,
                new ParameterizedTypeReference <List<PatientDTO>>() {});

        List<PatientDTO> patients = patientResponse.getBody();
        return patients;
    }



    public List<PatientDTO> getPatientsWithoutDM(){
        List<PatientDTO> all = getAllPatients();
        List<PatientDTO> p_not_dm = new ArrayList<>();
        boolean exist; int i;
        List<DossierMedical> dms = dossierMedicalRepostory.findAll();


        for (PatientDTO p: all) {
            i=0; exist = false;
            while (i < dms.size() && !exist) {
                if (p.getId().equals(dms.get(i).getPatientId())) exist = true;
                i++;
            }
            if (!exist){
                p_not_dm.add(p);
            }
        }
        return p_not_dm;
    }
}
