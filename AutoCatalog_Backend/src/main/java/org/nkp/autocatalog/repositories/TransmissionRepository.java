package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    Optional<Transmission> findByName(String name);
}
