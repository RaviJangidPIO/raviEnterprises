package com.remind.Quicker.controller;

import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {




    @GetMapping("/login-error")
    public String loginError(){
        return "login_error";
    }

    @GetMapping("/login-page")
    public String login(Model model){
        return "login";
    }
}
