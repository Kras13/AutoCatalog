package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Brand;
import org.nkp.autocatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Category> findByName(String name);
}
