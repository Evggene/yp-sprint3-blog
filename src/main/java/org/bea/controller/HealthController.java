package org.bea.controller;

import org.bea.dao.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    public HealthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        userRepository.findAll();
        return ResponseEntity.ok("Server is UP");
    }
}