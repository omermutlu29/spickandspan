package com.spickandspan.productservice.user.application.controller;

import com.spickandspan.productservice.user.application.service.UserService;
import com.spickandspan.productservice.user.domain.dto.req.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(this.userService.createUser(request));
    }




}