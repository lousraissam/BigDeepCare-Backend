package esi_cardio.com.auth_cardio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RessourceControlller {

    @RequestMapping({"/helloadministration"})
    public String helloAdministration(){
        return "Hello Administration";
    }

    @RequestMapping({"/helloadmin"})
    public String helloAdmin(){
        return "Hello Admin";
    }

    @RequestMapping({"/hellomedecin"})
    public String helloMedecin(){
        return "Hello Medecin";
    }

    @RequestMapping({"/helloaidesoignant"})
    public String helloAideSoignant(){
        return "Hello AideSoignant";
    }

    @RequestMapping({"/hellopatient"})
    public String helloPatient(){
        return "Hello Patient";
    }
}
