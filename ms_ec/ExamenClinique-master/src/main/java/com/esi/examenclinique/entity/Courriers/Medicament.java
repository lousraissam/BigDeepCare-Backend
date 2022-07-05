package com.esi.examenclinique.entity.Courriers;

import com.esi.examenclinique.entity.Courriers.Ordonnance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int posologie;
    private int quantite;


    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "ordonnance_id")
    private Ordonnance ordonnance;
}
