package esi_cardio.com.auth_cardio.service;

import esi_cardio.com.auth_cardio.entity.Patient;
import esi_cardio.com.auth_cardio.entity.UserDAO;
import esi_cardio.com.auth_cardio.repository.PatientRepository;
import esi_cardio.com.auth_cardio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static esi_cardio.com.auth_cardio.Age.getAge;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RestTemplate restTemplate;


        //get methods
        public List<UserDAO> getUsers(){
            return userRepository.findAll();
        }

        public UserDAO getUserById(Long idUsr){
            return userRepository.findById(idUsr).orElse(null);
        }

        public UserDAO getUserByUsername(String Usr){
            return userRepository.findUserDAOByUsername(Usr);
         }

        public Patient getPatientById(Long idu){
        return patientRepository.findById(idu).orElse(null);
    }

    // get methode Medecin ---------------------------------------------------------
         public Collection<Patient> getPatientsByParent(UserDAO parent){
            return patientRepository.findPatientByParent(parent);
        }
        public UserDAO getMedecinByPatientId(Long idp){
            Patient patient = patientRepository.findById(idp).orElse(null);
            return patient.getParent();
        }

    //delete method
        public void deleteUser(Long idUsr){
            userRepository.deleteById(idUsr);
            //return "User Removed "+idUsr;
        }

        //update method
        public UserDAO updateUser(UserDAO user){
            UserDAO exsistingUser = userRepository.findById(user.getId()).orElse(null);
            exsistingUser.setUsername(user.getUsername());
            exsistingUser.setTelephone(user.getTelephone());
            exsistingUser.setRole(user.getRole());
            exsistingUser.setState(user.getState());
            exsistingUser.setPrenom(user.getPrenom());
            exsistingUser.setNom(user.getNom());
            exsistingUser.setAdresse(user.getAdresse());
            //exsistingUser.setParent(user.getParent());
            if(user.getChildren()!= null){
                exsistingUser.getChildren().addAll(user.getChildren());
                for (Patient p :user.getChildren()) {
                    p = patientRepository.findById(p.getId()).orElse(null);
                    p.setParent(user);
                    patientRepository.save(p);
                }
            }
            return userRepository.save(exsistingUser);
        }



    //update method Patient----not in github yet--------------------------------------------------------
    public Patient updatePatient(Patient p){
        Patient exsistingPatient = patientRepository.findById(p.getId()).orElse(null);
        exsistingPatient.setUsername(p.getUsername());
        exsistingPatient.setCIN(p.getCIN());
        exsistingPatient.setNom(p.getNom());
        exsistingPatient.setPrenom(p.getPrenom());
        exsistingPatient.setDateNaissance(p.getDateNaissance());
        exsistingPatient.setSexe(p.getSexe());
        exsistingPatient.setAdresse(p.getAdresse());
        exsistingPatient.setGlyId(p.getGlyId());
        exsistingPatient.setBloodType(p.getBloodType());
        exsistingPatient.setDeviceKey(p.getDeviceKey());
        exsistingPatient.setType(p.getType());
        if(p.getDateNaissance()!=null){
            exsistingPatient.setAge(getAge(p.getDateNaissance()));
        }
        return patientRepository.save(exsistingPatient);
    }

    public List<Patient> getAllPatients(){
            return patientRepository.findAll();
    }

    public List<UserDAO> getAllMedecins(){
        return userRepository.getAllMedecins();
    }



}
