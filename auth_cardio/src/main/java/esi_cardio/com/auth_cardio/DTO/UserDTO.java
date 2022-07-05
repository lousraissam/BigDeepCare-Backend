package esi_cardio.com.auth_cardio.DTO;

import esi_cardio.com.auth_cardio.entity.UserDAO;
import lombok.Data;

import java.util.Collection;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String role;
    private UserDAO.State state ;

    private String telephone;
    private String adresse;
    private UserDAO parent;
    private Collection<UserDAO> children;
}
