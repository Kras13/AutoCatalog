package org.nkp.autocatalog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/fetch")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok("Endpoint reached...");
    }
}
