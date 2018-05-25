package com.gabrielczar.springrestoauth2jwt.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorRestController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, produces = "application/json")
    public ResponseEntity error() {
        return ResponseEntity.badRequest().body("{ \"error\": \"This content doesn't exist or is unauthorized!\"}");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
