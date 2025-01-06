package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Car;
import org.nkp.autocatalog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT DISTINCT c " +
            "FROM Car c " +
            "LEFT JOIN c.carFeatures cf " +
            "LEFT JOIN c.model.brand mb " +
            "WHERE " +
            "(:brandId IS NULL OR mb.id = :brandId) AND " +
            "(:modelId IS NULL OR c.model.id = :modelId) AND " +
            "(:transmissionId IS NULL OR c.transmission.id = :transmissionId) AND " +
            "(:fuelId IS NULL OR c.fuel.id = :fuelId) AND " +
            "(:categoryId IS NULL OR c.category.id = :categoryId) AND " +
            "(:featureIds IS NULL OR cf.feature.id IN :featureIds) AND " +
            "(cast(:fromDate as date) IS NULL OR c.dateManufactured >= :fromDate) AND " +
            "(cast(:untilDate as date) IS NULL OR c.dateManufactured <= :untilDate) " +
            "order by c.id")
    Page<Car> filterCarPages(
            Pageable pageable,
            Long brandId,
            Long modelId,
            Long transmissionId,
            Long fuelId,
            Long categoryId,
            List<Long> featureIds,
            java.sql.Date fromDate,
            java.sql.Date untilDate);

    List<Car> findByUser(User user);

    List<Car> findAllByOrderByIdAsc();
}
