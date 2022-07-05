package esi_cardio.com.auth_cardio.controller;

import esi_cardio.com.auth_cardio.entity.Patient;
import esi_cardio.com.auth_cardio.entity.UserDAO;
import esi_cardio.com.auth_cardio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("aideS")
public class aideSController {

    @Autowired
    private UserService userService;


    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return userService.getAllPatients();
    }

    @GetMapping("/medecins")
    public List<UserDAO> getAllMedecins(){
        return userService.getAllMedecins();
    }

    @GetMapping("users/{id}")
    public UserDAO getUserById(@PathVariable(name = "id") Long idUsr){
        return userService.getUserById(idUsr);
    }

    @PutMapping("/update/{id}")
    public UserDAO linkMP(@RequestBody UserDAO user){
        return userService.updateUser(user);
    }
}
