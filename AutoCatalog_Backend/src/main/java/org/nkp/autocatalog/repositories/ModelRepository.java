package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository  extends JpaRepository<Model, Long> {
    Optional<Model> findByName(String name);
    List<Model> findByBrandId(Long brandId);
}
