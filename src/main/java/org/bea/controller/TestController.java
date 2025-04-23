package org.bea.controller;

import org.bea.dao.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<String> health() {
        var r = userRepository.findAll();
        return ResponseEntity.ok(r.toString());
    }
}