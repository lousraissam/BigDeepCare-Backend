package com.esi.examenclinique.entity.Courriers;

import com.esi.examenclinique.entity.ExamenClinique;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor @Entity
@Table(name = "ORDONNANCE_TBL")
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    private String doctor;
    private String patient;

    @OneToOne(mappedBy = "ordonnance", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Medicament medicaments;

    //Autre medications
    private String autre;

    @JsonIgnore
    @OneToOne
    @MapsId
    // pk of emp tbl is used as pk for ord tbl
    private ExamenClinique examenClinique;
}
