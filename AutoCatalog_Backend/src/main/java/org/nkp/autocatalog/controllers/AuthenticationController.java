package org.nkp.autocatalog.controllers;

import org.nkp.autocatalog.models.AuthenticationRequest;
import org.nkp.autocatalog.models.AuthenticationResponse;
import org.nkp.autocatalog.models.RegisterRequest;
import org.nkp.autocatalog.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
//        return ResponseEntity.ok("Endpoint reached...");

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
