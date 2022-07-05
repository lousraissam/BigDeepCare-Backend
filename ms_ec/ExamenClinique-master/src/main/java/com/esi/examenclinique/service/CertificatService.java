package com.esi.examenclinique.service;

import com.esi.examenclinique.entity.Courriers.Certificat;
import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.repository.CertificatRepository;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificatService {
    @Autowired
    private CertificatRepository certificatRepository;
    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;
    @Autowired
    private ExamenCliniqueService examenCliniqueService;

    // POST
    public Certificat saveCertificat(Long idec, Certificat certificat) {
        ExamenClinique ec =  examenCliniqueRepository.findById(idec).orElse(null);
        ec.setCertificat(certificat);
        certificat.setExamenClinique(ec);
        //examenCliniqueRepository.save(ec);
        return certificatRepository.save(certificat);
    }

    // GET
    public List<Certificat> getCertificats() {
        return certificatRepository.findAll();
    }

    public Certificat getCertificatById(Long id) {
        return certificatRepository.findById(id).orElse(null);
    }

    // PUT
    public Certificat updateCertificat(Certificat certificat) {
        Certificat existingCertificat = certificatRepository.findById(certificat.getId()).orElse(null);
        existingCertificat.setPatientBD(certificat.getPatientBD());
        existingCertificat.setPatient(certificat.getPatient());
        existingCertificat.setDoctor(certificat.getDoctor());
        existingCertificat.setCause(certificat.getCause());
        existingCertificat.setRecommandation(certificat.getRecommandation());
        existingCertificat.setExamenClinique(certificat.getExamenClinique());
        return certificatRepository.save(existingCertificat);
    }

    // DELETE
    public String deleteCertificat(Long idCert) {
        ExamenClinique ec =  examenCliniqueService.getExamenCliniqueById(idCert);
        ec.setCertificat(null);
        certificatRepository.deleteById(idCert);
        return "Certificat " + idCert + " supprimé !! ";
    }

}
