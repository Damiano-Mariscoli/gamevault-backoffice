package org.gamevault.java.spring_gamevault.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping

public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html in templates
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "logout"; // login.html in templates
    }
}
