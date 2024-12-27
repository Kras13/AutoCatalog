package org.nkp.autocatalog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

public class BaseController {
    public static ResponseEntity<?> FormatBadRequest(BindingResult bindingResult) {
        var readableErrors = new ArrayList<String>();
        var errors = bindingResult.getAllErrors();

        for (var error: errors) {
            readableErrors.add(error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(readableErrors);
    }
}
