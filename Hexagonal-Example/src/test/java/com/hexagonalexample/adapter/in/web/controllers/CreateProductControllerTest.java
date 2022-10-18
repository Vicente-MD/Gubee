package com.hexagonalexample.adapter.in.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagonalexample.adapter.out.persistence.dto.ProductRepositoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class CreateProductControllerTest {

    private MockMvc mockMvc;

    private CreateProductController createProductController;

    private ObjectMapper objectMapper;

    private ProductRepositoryDTO createProductRequest(String name) {
        return ProductRepositoryDTO.builder()
                .name(name)
                .quantity(5)
                .price(8000.0)
                .build();
    }

    @Autowired
    public CreateProductControllerTest(MockMvc mockMvc, CreateProductController createProductController, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.createProductController = createProductController;
        this.objectMapper = objectMapper;
    }

    @Test
    void shouldCreateANewProduct() throws Exception{
        // given
        var product = createProductRequest("Produto de teste");
        var body = objectMapper.writeValueAsString(product);

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // then
        resultActions.andExpect(status().isCreated());
    }
}