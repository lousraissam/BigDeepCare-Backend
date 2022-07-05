package esi_cardio.com.auth_cardio.controller;

import esi_cardio.com.auth_cardio.entity.Patient;
import esi_cardio.com.auth_cardio.entity.UserDAO;
import esi_cardio.com.auth_cardio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

        @Autowired
        private UserService userService;


        @GetMapping("")
        public List<UserDAO> getUsers(){
            return userService.getUsers();
        }

        @GetMapping("/{id}")
        public UserDAO getUserById(@PathVariable(name = "id") Long idUsr){
            return userService.getUserById(idUsr);
        }

        @GetMapping("/{id}/medecin")
        public UserDAO getMedecinByPatientId(@PathVariable(name = "id") Long idP){
            return userService.getMedecinByPatientId(idP);
        }

        @GetMapping("/medecin/{id}/patients")
            public Collection<Patient> getPatientsByMedecin(@PathVariable(name = "id") Long idUsr){
                UserDAO medecin = userService.getUserById(idUsr);
                return userService.getPatientsByParent(medecin);
            }


        @PutMapping("/update/{id}")
        public UserDAO updateUser(@RequestBody UserDAO user){

            return userService.updateUser(user);
        }

        @DeleteMapping("/delete/{id}")
        public void deleteUser(@PathVariable(name = "id") Long idUsr){
            userService.deleteUser(idUsr);
        }

        // specific to patient -------------------------------------------
        @GetMapping("/patient/{id}")
        public Patient getPatientById(@PathVariable(name = "id") Long idu){
            return userService.getPatientById(idu);
        }


        @PutMapping("patient/update/{id}")
        public Patient updatePatient(@RequestBody Patient patient){
            return userService.updatePatient(patient);
        }


        @GetMapping("/get/{username}")
        public UserDAO getUserByUsername(@PathVariable(name = "username") String username){
            return userService.getUserByUsername(username);
        }


}
