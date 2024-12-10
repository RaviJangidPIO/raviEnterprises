package com.remind.Quicker.service.publicServices;

import com.remind.Quicker.dto.requestRestDto.CustomUserRequestRestDto;
import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.entities.Product;
import com.remind.Quicker.repository.CustomUserRepository;
import com.remind.Quicker.repository.ProductRepository;
import com.remind.Quicker.utils.PageDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class PublicService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    public void uploadData(CustomUserRequestRestDto requestCustomDto, MultipartFile file) throws IOException {
        CustomUser currentUser = modelMapper.map(requestCustomDto,CustomUser.class);
        currentUser.setPassword(passwordEncoder.encode(requestCustomDto.getPassword()));
        currentUser.setRole("USER");
        currentUser.setProfileImage(Base64.getEncoder().encodeToString(file.getBytes()));
        currentUser.setImageOriginalName(file.getName());
        currentUser.setType(file.getContentType());
        customUserRepository.save(currentUser);
    }

    public List<Product> getSomeProduct(PageDetail pageDetail) {
        PageRequest pageable = PageRequest.of(pageDetail.getPageNumber(),pageDetail.getPageSize());
        Page<Product> pagesData = productRepository.findAll(pageable);
        List<Product> productList =  pagesData.getContent();
        return productList;
    }

    public List<Product> getproducts() {
        return productRepository.findAll();
    }
}
