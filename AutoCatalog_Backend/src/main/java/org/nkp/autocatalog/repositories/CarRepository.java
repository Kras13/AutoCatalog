package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c " +
            "FROM Car c " +
            "JOIN c.carFeatures cf " +
            "WHERE " +
            "(:modelId IS NULL OR c.model.id = :modelId) AND " +
            "(:transmissionId IS NULL OR c.transmission.id = :transmissionId) AND " +
            "(:fuelId IS NULL OR c.fuel.id = :fuelId) AND " +
            "(:categoryId IS NULL OR c.category.id = :categoryId) AND " +
            "(:featureIds IS NULL OR cf.feature.id IN :featureIds) AND " +
            "(:fromDate IS NULL OR c.dateManufactured >= :fromDate) AND " +
            "(:untilDate IS NULL OR c.dateManufactured <= :untilDate)")
    Page<Car> filterCarPages(
            Pageable pageable,
            Long modelId,
            Long transmissionId,
            Long fuelId,
            Long categoryId,
            List<Long> featureIds,
            Date fromDate,
            Date untilDate);
}
