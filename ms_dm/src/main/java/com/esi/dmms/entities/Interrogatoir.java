package com.esi.dmms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "INTERO_TBL")
public class Interrogatoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // facteures de risque
    private String colesterol;
    private String sedantarite;

    // signes de risques CV
    private Boolean dyspne;
    private Boolean douleur_torasique;
    private Boolean palpitations;
    private Boolean syncope;
    private Boolean hypotimie;
    private String autre;



    @JsonIgnore
    @OneToOne
    @MapsId
    private Consultation consultation;
}
