package esi_cardio.com.auth_cardio.service;

import esi_cardio.com.auth_cardio.DTO.PatientDTO;
import esi_cardio.com.auth_cardio.DTO.UserDTO;
import esi_cardio.com.auth_cardio.RandomString;
import esi_cardio.com.auth_cardio.entity.Patient;
import esi_cardio.com.auth_cardio.entity.UserDAO;
import esi_cardio.com.auth_cardio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles=null;
        UserDAO user = userDao.findUserDAOByUsername(username);
        if (user != null) {
            if(user.getState().equals(UserDAO.State.ACTIVE)){
                roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
                return new User(user.getUsername(), user.getPassword(), roles);
            }else if (user.getState().equals(UserDAO.State.INACTIVE)){
                 // show apropriate msg if user is inactive
                    throw new DisabledException("User Disabled : " + username);
            }
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public UserDAO save(UserDTO user) {

            UserDAO newUser = new UserDAO();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setNom(user.getNom());
            newUser.setPrenom(user.getPrenom());
            newUser.setRole(user.getRole());
            newUser.setState(user.getState());
            newUser.setTelephone(user.getTelephone());
            newUser.setAdresse(user.getAdresse());

            return userDao.save(newUser);

    }
    public Patient save(PatientDTO patient) {

            Patient newPatient = new Patient();
            newPatient.setUsername(patient.getUsername());
            newPatient.setPassword(bcryptEncoder.encode(patient.getPassword()));
            newPatient.setNom(patient.getNom());
            newPatient.setPrenom(patient.getPrenom());
            newPatient.setRole("ROLE_PATIENT");
            newPatient.setState(patient.getState());
            newPatient.setTelephone(patient.getTelephone());
            newPatient.setAdresse(patient.getAdresse());

            return userDao.save(newPatient);
    }
}
