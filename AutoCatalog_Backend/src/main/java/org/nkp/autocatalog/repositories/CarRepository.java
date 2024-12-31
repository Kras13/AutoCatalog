package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
