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
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "EVACUATION_TBL")
public class Evacuation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objet;
    private String zonetxt;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    @JsonIgnore
    @OneToOne
    @MapsId
    // pk of emp tbl is used as pk for ord tbl
    private ExamenClinique examenClinique;
}
