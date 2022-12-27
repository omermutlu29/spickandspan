package com.spickandspan.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/order")
public class HelloController {
    @GetMapping("/hello")
    public String hello(Principal principal) {

        return principal.getName()+"Hello from order";
    }
}
