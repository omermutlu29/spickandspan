package com.spickandspan.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class HelloController {
    @GetMapping
    public String hello(){
        return "Hello from order";
    }
}
