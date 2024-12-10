package com.remind.Quicker.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {  //this class execute once for every authenticated request.

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("AuthTokenFilter called for URI: {}", request.getRequestURI()); // only for tracking purpose of incoming request
        try {
            Cookie cookie = WebUtils.getCookie(request,"jwt-token");
            String jwt=null;
            if(cookie!=null){
                jwt = cookie.getValue();
            }

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                List<String> roles = jwtUtils.getRolesFromJwtToken(jwt);

                List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                logger.debug("Roles from JWT: {}", userDetails.getAuthorities());

                //sets additional details like client ip address and session id for this authentication using current request data.
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {

            Cookie cookie = new Cookie("jwt-token", null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setDomain("localhost");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            logger.error("Cannot set user authentication: {}", e);
        }
        // passes the request and response to the next filter chain,allowing the request to proceed if authentication is successful.
        filterChain.doFilter(request, response);
    }

    //for extracting the jwt token from incoming request.
//    private String parseJwt(HttpServletRequest request) {
//        String jwt = jwtUtils.getJwtFromHeader(request);
//        logger.debug("AuthTokenFilter.java: {}", jwt);
//        return jwt;
//    }
}