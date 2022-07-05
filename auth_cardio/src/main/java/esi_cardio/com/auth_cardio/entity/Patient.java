package esi_cardio.com.auth_cardio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends UserDAO{

    @Column
    private String CIN; // should be unique
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;
    @Column
    private Genders sexe ;
    public enum Genders {
        HOMME, FEMME
    }
    private Type type ;
    public enum Type {
        TYPE_1, TYPE_2
    }
    @Column
    private Integer age;
    private BloodType bloodType ;
    public enum BloodType {
        A, B, O, AB
    }
    @Column
    private String deviceKey;


    @ElementCollection
    @Column(name = "glycemie_ids")
    private List<Long> GlyId; // list of glycemie

    @JsonIgnore
    @ManyToOne
    private UserDAO parent;
}
