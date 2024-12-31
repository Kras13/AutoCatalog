package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.categories.CategoryModel;
import org.nkp.autocatalog.models.categories.CategoryCreateModel;
import org.nkp.autocatalog.models.categories.EditCategoryModel;
import org.nkp.autocatalog.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@PreAuthorize("hasAuthority('ADMIN')")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<CategoryModel> getAllCategories() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryCreateModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.create(model));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCategory(
            @Valid @RequestBody EditCategoryModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.edit(model));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
