package com.remind.Quicker.service.customer;

import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.principle.CustomerUserPrinciple;
import com.remind.Quicker.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailServiceImpl  implements UserDetailsService {
    @Autowired
    private CustomUserRepository customUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.findByEmail(username);
        if(customUser==null){
            throw new RuntimeException("user not found");
        }
        return new CustomerUserPrinciple(customUser);
    }
}
