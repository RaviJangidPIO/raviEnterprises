package com.remind.Quicker.controller.admin;

import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.entities.Product;
import com.remind.Quicker.repository.CustomUserRepository;
import com.remind.Quicker.repository.ProductRepository;
import com.remind.Quicker.service.admin.AdminService;
import com.remind.Quicker.utils.PageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restAdmin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/get-users")
    public List<CustomUser> getUsers(@RequestBody PageDetail pageDetail){
        PageRequest pageable = PageRequest.of(pageDetail.getPageNumber(),pageDetail.getPageSize());
        Page<CustomUser> pagesData = customUserRepository.findAll(pageable);
        List<CustomUser> customUsersList =  pagesData.getContent();
        pagesData.getContent().forEach(System.out::println);
        return customUsersList;
    }


    @PostMapping("/get-productsPaging")
    public List<Product> getProducts(@RequestBody PageDetail pageDetail){
        PageRequest pageable = PageRequest.of(pageDetail.getPageNumber(),pageDetail.getPageSize());
        Page<Product> pagesData = productRepository.findAll(pageable);
        List<Product> productList =  pagesData.getContent();
        return productList;
    }

    @GetMapping("/get-usersOnce")
    public List<CustomUser> getUsersOnce(){
        return adminService.getUsersOnce();
    }

    @GetMapping("/get-products")
    public List<Product> getProducts(){
        return adminService.getProducts();
    }

//    /api/restAdmin/deleteProductById
    @DeleteMapping("/deleteProductById")
    public void deleteByProductId(@RequestParam Long id){
        adminService.deleteProductById(id);
    }

    @DeleteMapping("/deleteUserById")
    public void deleteUserById(@RequestParam Long id){
        adminService.deleteCustomUserById(id);
    }



}
