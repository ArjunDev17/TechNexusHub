package com.codeneeti.technexushub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping("/current")
    public ResponseEntity<UserDetails>getcurrentUser(Principal principal){
        String principalName = principal.getName();
        return new ResponseEntity<>(userDetailsService.loadUserByUsername(principalName), HttpStatus.OK);
    }
}
// public class AuthController {
//    @GetMapping("/current")
//    public ResponseEntity<String>getcurrentUser(Principal principal){
//        String principalName = principal.getName();
//        return new ResponseEntity<>(principalName, HttpStatus.OK);
//    }
//}
