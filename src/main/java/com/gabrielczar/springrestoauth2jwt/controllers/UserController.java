package com.gabrielczar.springrestoauth2jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity home() {
        return ResponseEntity.ok("{ \"message\": \"Hello World\" }");
    }

}
