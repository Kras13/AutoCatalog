package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.authentication.AuthenticationRequest;
import org.nkp.autocatalog.models.authentication.RegisterRequest;
import org.nkp.autocatalog.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @Valid @RequestBody AuthenticationRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        try {
            return ResponseEntity.ok(service.authenticate(request));
        }
        catch(BadCredentialsException e) {
            return ResponseEntity.badRequest().body("User with such credentials does not exist");
        }
    }
}
