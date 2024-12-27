package org.nkp.autocatalog.models.categories;

public class CategoryModel {
    private Long id;
    private String name;

    public CategoryModel() {
    }

    public CategoryModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
