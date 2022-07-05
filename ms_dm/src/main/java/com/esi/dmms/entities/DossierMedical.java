package com.esi.dmms.entities;

import com.esi.dmms.DTO.PatientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "DOSSIER_MEDICAL_TBL")
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "patientId")
    private Long patientId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    @JsonIgnore
    @OneToMany(mappedBy="dm")
    private List<Consultation> consultations;


    //@OneToMany(mappedBy="dossierMedical",cascade = CascadeType.ALL, orphanRemoval = true)

    @OneToMany(mappedBy="dossierMedical",cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Antecedent> antecedents;


    public DossierMedical(Long id, String description) {
        this.id = id;
        this.description = description;
    }

}
