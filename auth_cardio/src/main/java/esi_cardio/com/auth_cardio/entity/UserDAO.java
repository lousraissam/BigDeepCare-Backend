package esi_cardio.com.auth_cardio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private State state ;
    public enum State {
        ACTIVE, INACTIVE
    }
    @Column
    private String telephone;
    @Column
    private String adresse;

    @OneToMany(mappedBy="parent")
    private Collection<Patient> children;

}
