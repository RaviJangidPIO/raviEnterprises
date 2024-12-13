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





}
