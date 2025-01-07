package org.nkp.autocatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nkp.autocatalog.config.JwtService;
import org.nkp.autocatalog.controllers.BrandController;
import org.nkp.autocatalog.models.brands.BrandCreateModel;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.services.contracts.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = BrandController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BrandService brandService;
    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;
    private BrandModel brandModel;
    private BrandCreateModel createModel;

    @BeforeEach
    public void init() {
        brandModel = new BrandModel(1L, "demo");

        createModel = new BrandCreateModel();
        createModel.setName("demo");
    }

    @Test
    public void BrandController_CreateBrand_ReturnCreated() throws Exception {
        when(brandService.create(any(BrandCreateModel.class))).thenReturn(brandModel);

        var response = mockMvc.perform(
                post("/api/brand/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createModel)));

        response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(brandModel.getName())));
    }

    @Test
    public void BrandController_Fetch_ReturnBrandModel() throws Exception {
        var models = List.of(brandModel, brandModel, brandModel);

        when(brandService.getAll()).thenReturn(models);

        var response = mockMvc.perform(
                get("/api/brand/fetch")
                        .contentType(MediaType.APPLICATION_JSON));

        response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(models.size())));
    }
}
