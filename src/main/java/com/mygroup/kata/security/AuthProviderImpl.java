//package com.mygroup.kata.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
///**
// * @author Neil Alishev
// */
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public AuthProviderImpl(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//
//        UserDetails personDetails = userDetailsService.loadUserByUsername(username);
//
//        String password = authentication.getCredentials().toString();
//
//        if (!password.equals(personDetails.getPassword()))
//            throw new BadCredentialsException("Incorrect password");
//
//        return new UsernamePasswordAuthenticationToken(personDetails, password,
//                Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
//
