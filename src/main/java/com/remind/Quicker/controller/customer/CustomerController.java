package com.remind.Quicker.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @GetMapping("/cart-orders")
    public String ordersOfCart(){
        return "customer_addCart";
    }
}
