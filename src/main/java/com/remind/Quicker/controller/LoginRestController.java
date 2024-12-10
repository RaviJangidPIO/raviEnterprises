package com.remind.Quicker.controller;

import com.remind.Quicker.dto.requestRestDto.LoginRequest;
import com.remind.Quicker.dto.responseDto.LoginResponse;
import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.jwt.JwtUtils;
import com.remind.Quicker.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoginRestController {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserRepository customUserRepository;

    @GetMapping("/authenticated-user")
    public ResponseEntity<CustomUser> getAuthenticatedUser(Principal principal) {
        // current logged-in user email
        String email = principal.getName();
        CustomUser customer = customUserRepository.findByEmail(email);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("signin-login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String jwtToken = jwtUtils.generateTokenFromUsername(userDetails,roles);

            LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException exception) {
            Map<String,Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
