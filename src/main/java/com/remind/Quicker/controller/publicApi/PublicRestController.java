package com.remind.Quicker.controller.publicApi;

import com.remind.Quicker.dto.requestRestDto.CustomUserRequestRestDto;
import com.remind.Quicker.entities.Product;
import com.remind.Quicker.service.publicServices.PublicService;
import com.remind.Quicker.utils.PageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rest-public")
public class PublicRestController {

    @Autowired
    private PublicService publicService;

// /api/rest-public/uploadInDB"

    @GetMapping("/get_allProducts")
    public List<Product> getAllProducts(){
        return publicService.getproducts();
    }

    @GetMapping("/get-someProducts")
    public List<Product> getSomeProducts(){
        PageDetail pageDetail = new PageDetail(0,4);
        List<Product> productList = publicService.getSomeProduct(pageDetail);
        return productList;
    }

    @PostMapping("/get-productsForCustomer")
    public List<Product> getProducts(@RequestBody PageDetail pageDetail){
        List<Product> productList = publicService.getSomeProduct(pageDetail);
        return productList;
    }

}
