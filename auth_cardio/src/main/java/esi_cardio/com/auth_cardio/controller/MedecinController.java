package esi_cardio.com.auth_cardio.controller;

import esi_cardio.com.auth_cardio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Med")
public class MedecinController {

    @Autowired
    private UserService userService;



}
