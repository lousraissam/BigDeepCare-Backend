package com.esi.dmms.controller;

import com.esi.dmms.DTO.PatientDTO;
import com.esi.dmms.entities.Antecedent;
import com.esi.dmms.entities.DossierMedical;
import com.esi.dmms.repository.DossierMedicalRepostory;
import com.esi.dmms.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("DM")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @Autowired
    private DossierMedicalRepostory dossierMedicalRepostory;

    @PostMapping("/add")
    public DossierMedical addDossierMedical(@RequestBody DossierMedical dossierMedical){
        return dossierMedicalService.saveDossierMedical(dossierMedical);
    }
    @PostMapping("/addAll")
    public List<DossierMedical> addDossiersMedicaux(@RequestBody List<DossierMedical> dossiersMedicaux){
        return dossierMedicalService.saveDossiersMedicaux(dossiersMedicaux);
    }

    @GetMapping("")
    public List<DossierMedical> getDossiersMedicaux(){
        return dossierMedicalService.getDossiersMedicaux();
    }
    @GetMapping("/{id}")
    public DossierMedical getDossierMedicalById(@PathVariable(name = "id") Long idDM){
        return dossierMedicalService.getDossierMedicalById(idDM);
    }
    @GetMapping("patient/{id}")
    public DossierMedical getDMByPatientId(@PathVariable(name = "id") Long idp){
        return dossierMedicalService.getDossierMedicalByPid(idp);
    }
    @GetMapping("patients")
    public List<PatientDTO> getPatients(){
        return dossierMedicalService.getAllPatients();
    }


    @GetMapping("/{id}/patient")
    public PatientDTO getPatientByDMId(@PathVariable(name = "id") Long idDM){
        return dossierMedicalService.getPatientByDMId(idDM);
    }

    @PutMapping("/update")
    public DossierMedical updateDossierMedical(@RequestBody DossierMedical dossierMedical){

        return dossierMedicalService.updateDossierMedical(dossierMedical);
    }

    @PutMapping("/update/{idp}")
    public DossierMedical updateDossierMedical(@RequestBody Collection<Antecedent> a, @PathVariable(name = "idp") Long idp){

        return dossierMedicalService.updateDMAntecedants(a, idp);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDossierMedical(@PathVariable(name = "id") Long idDM){
        return dossierMedicalService.deleteDossierMedical(idDM);
    }

    @GetMapping("patients_No_DM")
    public List<PatientDTO> getPatientsWithoutDM(){
        return dossierMedicalService.getPatientsWithoutDM();
    }

}
