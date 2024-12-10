package com.remind.Quicker.controller.publicApi;

import com.remind.Quicker.dto.requestRestDto.CustomUserRequestRestDto;
import com.remind.Quicker.service.publicServices.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private PublicService publicService;

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){
        return "aboutUs";
    }

//    /api/public/all-product
    @GetMapping("/all-product")
    public String getProducts(){
        return "customer_allProducts";
    }

    //http://localhost:8080/api/public/signup
    @GetMapping("/signup")
    public String signup(){
        return "customer_signup";
    }

    // /api/public/uploadInDB
    @PostMapping("/uploadInDB")
    public String uploadInDB(@ModelAttribute CustomUserRequestRestDto requestCustomDto, @RequestPart("profileImage") MultipartFile file) throws IOException {
        publicService.uploadData(requestCustomDto,file);
        return "index";
    }



}
