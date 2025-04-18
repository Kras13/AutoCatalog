package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findByName(String name);
}
