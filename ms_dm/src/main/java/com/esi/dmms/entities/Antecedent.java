package com.esi.dmms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Antecedent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public enum Types {
        CHIRURGICAUX, FAMILIAUX, MEDICAUX, HABITUDES
    }
    private Types type = Types.HABITUDES; // default
    private String nom;
    private String remarque;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private DossierMedical dossierMedical;



}
