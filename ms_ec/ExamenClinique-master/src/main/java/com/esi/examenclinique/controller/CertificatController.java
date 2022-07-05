package com.esi.examenclinique.controller;

import com.esi.examenclinique.entity.Courriers.Certificat;
import com.esi.examenclinique.service.CertificatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CertificatController {

    @Autowired
    private CertificatService certificatService;

    // POST
    @PostMapping("{idec}/addCertificat")
    public Certificat addCertificat(@PathVariable Long idec,@RequestBody Certificat certificat) {
        return certificatService.saveCertificat(idec, certificat);
    }

    // GET
    @GetMapping("/certificats")
    public List<Certificat> findAllCertificats() {
        return certificatService.getCertificats();
    }

    @GetMapping("/certificatById/{id}")
    public Certificat findCertificatById(@PathVariable Long id) {
        return certificatService.getCertificatById(id);
    }


    // PUT
    @PutMapping("/updateCert")
    public Certificat updateCertificat(@RequestBody Certificat certificat) {
        return certificatService.updateCertificat(certificat);
    }

    // DELETE
    @DeleteMapping("/deleteCert/{id}")
    public String deleteCertificat(@PathVariable Long id) {
        return certificatService.deleteCertificat(id);
    }

}
