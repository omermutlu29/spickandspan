package com.spickandspan.user.product.application.controller;

import com.spickandspan.user.user.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/products/api/v1")
public class ProductController {

    private final UserService userService;
}
