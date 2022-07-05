package esi_cardio.com.auth_cardio.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import esi_cardio.com.auth_cardio.entity.Patient;
import esi_cardio.com.auth_cardio.entity.UserDAO;
import lombok.Data;

import javax.persistence.Column;
import java.util.Collection;
import java.util.Date;

@Data
public class PatientDTO {
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String role;
    private UserDAO.State state ;

    private String telephone;

    private String adresse;
    private UserDAO parent;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;
    private Patient.Genders sexe ;
    public enum Genders {
        HOMME, FEMME
    }
    private Integer age;
    private Patient.BloodType bloodType ;
    public enum BloodType {
        A, B, O, AB
    }
    private Collection<UserDAO> children;
}
