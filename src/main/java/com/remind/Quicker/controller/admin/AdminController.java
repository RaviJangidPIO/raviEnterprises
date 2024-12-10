package com.remind.Quicker.controller.admin;

import com.remind.Quicker.dto.requestRestDto.CustomUserRequestRestDto;
import com.remind.Quicker.dto.requestRestDto.ProductRequestDto;
import com.remind.Quicker.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

//    /api/admin/dashBoard
    @GetMapping("/dashBoard")
    public String dashBoard(){
        return "admin_dashBoard";
    }
//    /api/admin/products
    @GetMapping("/products")
    public String showProducts(){
        return "admin_allProducts";
    }
//    /api/admin/customer
    @GetMapping("/customer")
    public String showCustomer(){
            return "admin_allCustomer";
    }
// /api/admin/signup
    @GetMapping("/signup")
    public String adminSignup(){
        return "admin_signup";
    }

    @GetMapping("/addProduct")
    public String addProduct(){
        return "admin_addProduct";
    }

//    Post Request
//   /api/admin/upload-product
    @PostMapping("/upload-product")
    public String uploadProduct(@ModelAttribute ProductRequestDto productRequestDto , @RequestPart("productImage") MultipartFile file) throws IOException {
        adminService.uploadProduct(productRequestDto,file);
        return "admin_addProduct";
    }
    //   /api/admin/uploadInDB
    @PostMapping("/uploadInDB") //uploading signup info in database
    public String uploadInDB(@ModelAttribute CustomUserRequestRestDto requestCustomDto, @RequestPart("profileImage") MultipartFile file) throws IOException {
        adminService.addSignupInfo(requestCustomDto,file);
        return "admin_dashBoard";
    }


}
