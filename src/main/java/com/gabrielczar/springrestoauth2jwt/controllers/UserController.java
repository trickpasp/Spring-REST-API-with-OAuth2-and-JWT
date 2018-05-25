package com.gabrielczar.springrestoauth2jwt.controllers;

import com.gabrielczar.springrestoauth2jwt.domain.User;
import com.gabrielczar.springrestoauth2jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/adm")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity adm(Principal principal) {
        return ResponseEntity.ok("ADMIN: " + principal.getName());
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity user(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public List<User> getUsers(){
        return userRepository.findAll();
    }


}
