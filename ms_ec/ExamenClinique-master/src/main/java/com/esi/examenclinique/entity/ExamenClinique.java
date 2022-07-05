package com.esi.examenclinique.entity;

import com.esi.examenclinique.entity.Courriers.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXAMEN_CLINIQUE_TBL")
public class ExamenClinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    private String taille;
    private String poid;
    private String tension;
    private String palpitation_pouls;
    private String oscultation;


    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.MERGE)
    // owner of relation is ordonnance tbl, it holds forign key
    // if we delete em , ord will be removed
    @PrimaryKeyJoinColumn
    // pk of em tbl will be used as fk for ord tbl
    private Ordonnance ordonnance;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Rapport rapport;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Certificat certificat;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Evacuation evacuation;

    @OneToOne(mappedBy = "examenClinique", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Orientation orientation;


    public ExamenClinique(Long id) {
        this.id = id;
    }
}
