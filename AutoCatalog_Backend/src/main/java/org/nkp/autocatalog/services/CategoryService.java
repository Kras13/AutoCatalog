package org.nkp.autocatalog.services;

import org.nkp.autocatalog.entities.Category;
import org.nkp.autocatalog.models.categories.CategoryModel;
import org.nkp.autocatalog.models.categories.CreateCategoryModel;
import org.nkp.autocatalog.models.categories.EditCategoryModel;
import org.nkp.autocatalog.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryModel create(CreateCategoryModel model) {
        if (categoryRepository.findByName(model.getName()).isPresent()) {
            throw new IllegalArgumentException("Category with such name already exists");
        }

        var category = new Category(model.getName());
        var savedCategory = categoryRepository.save(category);

        return new CategoryModel(savedCategory.getId(), savedCategory.getName());
    }

    public Boolean existsByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }

    public Boolean existsById(Long id) {
        return categoryRepository.findById(id).isPresent();
    }

    public List<CategoryModel> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(c -> new CategoryModel(c.getId(), c.getName()))
                .toList();
    }

    public CategoryModel edit(EditCategoryModel model) {
        var category = categoryRepository.findById(model.getId());

        if (!category.isPresent()) {
            throw new IllegalArgumentException("Category with such id does not exist");
        }

        var persistCategory = category.get();

        persistCategory.setName(model.getName());

        var savedCategory = categoryRepository.save(persistCategory);

        return new CategoryModel(savedCategory.getId(), savedCategory.getName());
    }

    public boolean deleteById(Long id) {
        var category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new IllegalArgumentException("Category with such id does not exist");
        }

        categoryRepository.delete(category.get());

        return true;
    }
}
