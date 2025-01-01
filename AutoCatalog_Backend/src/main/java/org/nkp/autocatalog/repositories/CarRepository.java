package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c " +
            "FROM Car c " +
            "WHERE " +
            "(:categoryId IS NULL OR c.category.id = :categoryId)")
    Page<Car> filterCarPages(Pageable pageable, Long categoryId);

}
