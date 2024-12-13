package com.remind.Quicker.controller.customer;

import com.remind.Quicker.dto.responseDto.OrderCartResponseDetails;
import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.repository.CustomUserRepository;
import com.remind.Quicker.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/rest-customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomUserRepository customUserRepository;

    @PostMapping("/add-cart")
    public void addCart(@RequestParam("id") Long id , @RequestParam("pieces") Long pieces){
        System.out.println("hello");
        customerService.updateCart(id,pieces);
    }


    @GetMapping("/placed-product")
    public List<OrderCartResponseDetails> placedProduct(){
        return customerService.getPlacedProduct();
    }


    @GetMapping("/confirm-order")
    public Boolean placedOrder(Principal principal){
        String email = principal.getName();
        CustomUser customer = customUserRepository.findByEmail(email);
        return customerService.confirmedOrder(customer);
    }
}
