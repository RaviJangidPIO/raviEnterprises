package com.remind.Quicker.service.admin;

import com.remind.Quicker.dto.requestRestDto.CustomUserRequestRestDto;
import com.remind.Quicker.dto.requestRestDto.ProductRequestDto;
import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.entities.Product;
import com.remind.Quicker.repository.CustomUserRepository;
import com.remind.Quicker.repository.ProductRepository;
import com.remind.Quicker.utils.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private CustomUserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    public List<CustomUser> getProducts() {
//
//    }

    public void uploadProduct(ProductRequestDto productRequestDto, MultipartFile file) throws IOException {
        Product product = modelMapper.map(productRequestDto,Product.class);
        product.setDeleteStatus(DeleteStatus.ACTIVE.toString());
        product.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
        productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getDeleteStatus().equals(DeleteStatus.ACTIVE.toString())).toList();
    }

    public void addSignupInfo(CustomUserRequestRestDto requestCustomDto, MultipartFile file) throws IOException {
        CustomUser currentUser = modelMapper.map(requestCustomDto,CustomUser.class);
        currentUser.setPassword(passwordEncoder.encode(requestCustomDto.getPassword()));
        currentUser.setRole("ADMIN");
        currentUser.setStatus(CustomerStatus.ACTIVE.toString());
        currentUser.setOrderStatus(Status.ACTIVE.toString());
        currentUser.setDeleteStatus(DeleteStatus.ACTIVE.toString());
        currentUser.setProfileImage(Base64.getEncoder().encodeToString(file.getBytes()));
        currentUser.setImageOriginalName(file.getName());
        currentUser.setType(file.getContentType());
        userRepository.save(currentUser);
    }

//    public List<CustomUser> getUsers(PageDetail pageDetail) {
//        PageRequest pageable = PageRequest.of(pageDetail.getPageNumber(),pageDetail.getPageSize());
//        Page<CustomUser> pagesData = userRepository.findAll(pageable);
//        List<CustomUser> customUsersList =  pagesData.getContent();
//        return customUsersList;
//    }
    public List<CustomUser> getUsersOnce() {
        return userRepository
                .findAll()
                .stream()
                .filter(user-> user.getDeleteStatus().equals(DeleteStatus.ACTIVE.toString())).toList();
    }

    public void deleteProductById(Long id) {
        productRepository.deactivateProduct(id);
    }

    public void deleteCustomUserById(Long id){
        Optional<CustomUser> user = userRepository.findById(id);
        user.ifPresent(currentUser->{
            if((currentUser.getRole().equals(Roles.ADMIN.toString())) || currentUser.getRole().equals(Roles.OWNER.toString())){

            }else{
                userRepository.deactivateUser(id);
            }
        });

    }
}
