package com.esi.dmms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "CONSULTATION_TBL")
public class Consultation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
        @PrimaryKeyJoinColumn
        private Interrogatoir interrogatoir;

        @CreationTimestamp
        @Column(updatable = false)
        private LocalDate createDate;

        @Column
        //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private String appointmentDate;



        @Column
        private Long ecId; // EC (physisque)

        @ElementCollection
        @Column
        private List<Long> bpId; // list of BP

        public enum States {
                EN_ATTENTE, EN_COURS, TERMINER
        }
        private States state = States.EN_ATTENTE; // default

        @ManyToOne
        @JoinColumn(name="dm_id", nullable=false)
        private DossierMedical dm;
    }