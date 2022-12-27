package com.spickandspan.animal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello from animal";
    }
}
