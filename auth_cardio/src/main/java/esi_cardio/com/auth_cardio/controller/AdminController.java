package esi_cardio.com.auth_cardio.controller;


import esi_cardio.com.auth_cardio.entity.UserDAO;
import esi_cardio.com.auth_cardio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("users/{id}")
    public UserDAO getUserById(@PathVariable(name = "id") Long idUsr){
        return userService.getUserById(idUsr);
    }


}
