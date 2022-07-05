package com.esi.dmms.service;

import com.esi.dmms.DTO.BPDTOs.BPDTO;
import com.esi.dmms.DTO.EcDTOs.ECDTO;
import com.esi.dmms.DTO.PatientDTO;
import com.esi.dmms.DTO.ResponseTemplateConsultationsPatient;
import com.esi.dmms.entities.Consultation;
import com.esi.dmms.entities.DossierMedical;
import com.esi.dmms.repository.ConsultationRepository;
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
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @Autowired
    private DossierMedicalRepostory dossierMedicalRepostory;

    @Autowired
    private RestTemplate restTemplate;

    //post methods
    public Consultation saveConsultation(Consultation consultation){
        return consultationRepository.save(consultation);
    }


    //get methods
    public List<Consultation> getConsultations(){
        return consultationRepository.findAll();
    }

    public Consultation getConsultationById(Long idC){
        return consultationRepository.findById(idC).orElse(null);
    }



    public ECDTO getECsByC(Consultation c){
        ResponseEntity<ECDTO> ecResponse =  restTemplate.exchange(
                "http://localhost:9003/EC/"+c.getEcId(),HttpMethod.GET,null,ECDTO.class);
        ECDTO ecdto = ecResponse.getBody();
        return ecdto;
    }

    public List<BPDTO> getBPsByC(Consultation c){
        List<BPDTO> bpdtoList = new ArrayList<>();

        for (Long bp:c.getBpId()) {
            ResponseEntity<BPDTO> bpResponse =  restTemplate.exchange(
                    "http://localhost:9004/BP/"+bp,HttpMethod.GET,null,BPDTO.class);
            BPDTO bilanParaclinique = bpResponse.getBody();
            bpdtoList.add(bilanParaclinique);
        }
        return bpdtoList;
    }

    //delete method
    public void deleteConsultation(Long idC){
        consultationRepository.deleteById(idC);

    }

    //update method
    public Consultation updateConsultation(Consultation consultation){
        Consultation exsistingC = consultationRepository.findById(consultation.getId()).orElse(null);
        exsistingC.setDm(consultation.getDm());
        exsistingC.setEcId(consultation.getEcId());
        exsistingC.setBpId(consultation.getBpId());
        exsistingC.setInterrogatoir(consultation.getInterrogatoir());
        exsistingC.setState(consultation.getState());
        exsistingC.setAppointmentDate(consultation.getAppointmentDate());

        return consultationRepository.save(exsistingC);
    }

    public List<Consultation> getConsultationByDM(Long idDM) {
        DossierMedical dm = dossierMedicalRepostory.findById(idDM).orElse(null);
        return consultationRepository.findConsultationByDm(dm);
    }

    public Consultation updateBP(Long idC, Collection<Long> idsBP){
        Consultation c = consultationRepository.findById(idC).orElse(null);
        c.getBpId().clear();
        c.getBpId().addAll(idsBP);
        return consultationRepository.save(c);

    }

    public Consultation updateState(Long idC, String state){
        Consultation c = consultationRepository.findById(idC).orElse(null);
        if (state.equals("EN_COURS")){
            c.setState(Consultation.States.EN_COURS);
        }else if(state.equals("TERMINER")){
            c.setState(Consultation.States.TERMINER);
        }
        return consultationRepository.save(c);
    }

    public ResponseTemplateConsultationsPatient getPatientByIdC(Long idC){
        Consultation consultation = consultationRepository.findById(idC).orElse(null);
        DossierMedical dm = consultation.getDm();
        PatientDTO patientDTO = dossierMedicalService.getPatientByDMId(dm.getId());
        ResponseTemplateConsultationsPatient response = new ResponseTemplateConsultationsPatient();
        response.setPatient(patientDTO);
        response.setConsultation(consultation);
        return response;
    }



    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    public List<Consultation> getConsultationByMedecin(Long idM) {

        String token = getBearerTokenHeader();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);

        ResponseEntity<List<PatientDTO>> patientResponse =  restTemplate.exchange(
                "http://localhost:8080/users/medecin/"+idM+"/patients", HttpMethod.GET, jwtEntity,
                new ParameterizedTypeReference<List<PatientDTO>>() {});

        List<PatientDTO> patients = patientResponse.getBody();
        List<Consultation> consultations = new ArrayList<>();

        for (PatientDTO p: patients) {
            DossierMedical dm = dossierMedicalRepostory.findDossierMedicalByPatientId(p.getId());
            consultations.addAll(consultationRepository.findConsultationByDm(dm));
        }
        return consultations;
    }

    public List<BPDTO> getBPsByidP(Long idp) {
        List<BPDTO> bpdtoList = new ArrayList<>();
        DossierMedical dm = dossierMedicalService.getDossierMedicalByPid(idp);
        List<Consultation> consultations = getConsultationByDM(dm.getId());


        for (Consultation c : consultations) {
            for (Long bp : c.getBpId()) {
                ResponseEntity<BPDTO> bpResponse = restTemplate.exchange(
                        "http://localhost:9004/BP/" + bp, HttpMethod.GET, null, BPDTO.class);
                BPDTO bilanParaclinique = bpResponse.getBody();
                bpdtoList.add(bilanParaclinique);
            }
        }
        return bpdtoList;
    }


}
