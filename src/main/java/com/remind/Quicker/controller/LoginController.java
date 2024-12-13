package com.remind.Quicker.controller;

import com.remind.Quicker.dto.requestRestDto.CustomUserRequestRestDto;
import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.repository.CustomUserRepository;
import com.remind.Quicker.service.publicServices.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private PublicService publicService;

    //http://localhost:8080/api/public/signup
    @GetMapping("/signup")
    public String signup(){
        return "customer_signup";
    }

    // /api/public/uploadInDB
    @PostMapping("/uploadInDB")
    public String uploadInDB(@ModelAttribute CustomUserRequestRestDto requestCustomDto, @RequestPart("profileImage") MultipartFile file) throws IOException {
        publicService.uploadData(requestCustomDto,file);
        return "login";
    }


    @GetMapping("/login-error")
    public String loginError(){
        return "login_error";
    }

    @GetMapping("/login-page")
    public String login(Model model){
        return "login";
    }
}
