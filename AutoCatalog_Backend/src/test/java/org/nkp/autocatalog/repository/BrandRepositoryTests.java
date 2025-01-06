package org.nkp.autocatalog.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nkp.autocatalog.entities.Brand;
import org.nkp.autocatalog.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection =  EmbeddedDatabaseConnection.H2)
public class BrandRepositoryTests {
    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void BrandRepository_Save_ReturnSavedBrand() {
        var brand = new Brand("DemoBrand", new HashSet<>());
        var savedBrand = brandRepository.save(brand);

        Assertions.assertThat(savedBrand).isNotNull();
        Assertions.assertThat(savedBrand.getId()).isGreaterThan(0);
    }

    @Test
    public void BrandRepository_GetAll_ReturnMoreThanOneBrand() {
        var brands =
                List.of(new Brand("demo", new HashSet<>()), new Brand("demo2", new HashSet<>()));

        var savedBrands = brandRepository.saveAll(brands);

        Assertions.assertThat(savedBrands).isNotNull();
        Assertions.assertThat(savedBrands.size()).isEqualTo(2);
    }
}
