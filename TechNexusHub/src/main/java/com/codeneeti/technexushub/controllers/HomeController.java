package com.codeneeti.technexushub.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {
    @GetMapping("/welcome")
    public String getMsg(){
        return  "Welcome here";
    }
}
