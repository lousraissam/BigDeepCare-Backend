package com.esi.dmms.controller;

import com.esi.dmms.DTO.BPDTOs.BPDTO;
import com.esi.dmms.DTO.EcDTOs.ECDTO;
import com.esi.dmms.DTO.ResponseTemplateConsultationsPatient;
import com.esi.dmms.entities.Consultation;
import com.esi.dmms.repository.ConsultationRepository;
import com.esi.dmms.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("Consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ConsultationRepository consultationRepository;

    @PostMapping("/add")
    public Consultation addConsultation(@RequestBody Consultation consultation) {
        return consultationService.saveConsultation(consultation);
    }

    @GetMapping("")
    public List<Consultation> getConsultations() {
        return consultationService.getConsultations();
    }

    @GetMapping("/{id}")
    public Consultation getConsultationById(@PathVariable(name = "id") Long idC) {
        return consultationService.getConsultationById(idC);
    }

    @GetMapping("/DM/{id}")
    public List<Consultation> getConsultationByDMId(@PathVariable(name = "id") Long idDM) {
        return consultationService.getConsultationByDM(idDM);
    }

    @GetMapping("/{id}/EC")
    public ECDTO getECsByC(@PathVariable(name = "id") Long idC) {
        Consultation c = consultationRepository.findById(idC).orElse(null);
        return consultationService.getECsByC(c);
    }

    @PutMapping("/{id}/updateBP")
    public Consultation updateBP(@PathVariable(name = "id") Long idC, @RequestBody Collection<Long> idsBP) {
        return consultationService.updateBP(idC, idsBP);
    }

    @PutMapping("/{id}/updateState")
    public Consultation updateState(@PathVariable(name = "id") Long idC, @RequestBody String state) {
        return consultationService.updateState(idC, state);
    }

    @GetMapping("/{id}/BP")
    public List<BPDTO> getBPsByC(@PathVariable(name = "id") Long idC) {
        Consultation c = consultationRepository.findById(idC).orElse(null);
        return consultationService.getBPsByC(c);
    }

    @GetMapping("/Patient/{id}/BP")
    public List<BPDTO> getBPsByidP(@PathVariable(name = "id") Long idp){
        return consultationService.getBPsByidP(idp);
    }

    @GetMapping("/{id}/Patient")
    public ResponseTemplateConsultationsPatient getPatientByIdC(@PathVariable(name = "id") Long idC) {
        return consultationService.getPatientByIdC(idC);
    }


    @PutMapping("/update")
    public Consultation updateConsultation(@RequestBody Consultation consultation) {
        return consultationService.updateConsultation(consultation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteConsultation(@PathVariable(name = "id") Long idC) {
        consultationService.deleteConsultation(idC);
    }

    @GetMapping("/medecin/{id}")
    public List<Consultation> getConsultationByMedecin(@PathVariable(name = "id") Long idM) {
        return consultationService.getConsultationByMedecin(idM);
    }
    @GetMapping("/medecin/{id}/all")
    public List<ResponseTemplateConsultationsPatient> getConsultationPatientByMedecin(@PathVariable(name = "id") Long idM){
        List<Consultation> consultations =  consultationService.getConsultationByMedecin(idM);
        List<ResponseTemplateConsultationsPatient> responseTemplateConsultationsPatients = new ArrayList<>();
        for (Consultation c:consultations) {
            responseTemplateConsultationsPatients.add(consultationService.getPatientByIdC(c.getId()));
        }
        return responseTemplateConsultationsPatients;
    }
}