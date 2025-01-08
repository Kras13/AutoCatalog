package org.nkp.autocatalog.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nkp.autocatalog.entities.Brand;
import org.nkp.autocatalog.models.brands.BrandCreateModel;
import org.nkp.autocatalog.repositories.BrandRepository;
import org.nkp.autocatalog.services.BrandServiceImpl;

import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    public void BrandService_CreateBrand_ReturnBrandModel() {
        var brand = new Brand("DemoBrand", new HashSet<>());
        var model = new BrandCreateModel();

        model.setName(brand.getName());

        Mockito.when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(brand);

        var savedBrand = brandService.create(model);

        Assertions.assertThat(savedBrand).isNotNull();
    }

    @Test
    public void BrandService_GetAllBrands_ReturnBrandModelList() {
        var brands = List.of(
                new Brand("demo1", new HashSet<>()),
                new Brand("demo2", new HashSet<>()));

        Mockito.when(brandRepository.findAll()).thenReturn(brands);

        var fetchedBrands = brandRepository.findAll();

        Assertions.assertThat(fetchedBrands).isNotNull();
        Assertions.assertThat(fetchedBrands.size()).isEqualTo(2);
    }
}
