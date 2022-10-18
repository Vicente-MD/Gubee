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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateProductControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public UpdateProductControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    private ProductRepositoryDTO createProductRequest(String name, int id) {
        return ProductRepositoryDTO.builder()
                .id(id)
                .name(name)
                .quantity(5)
                .price(8000.0)
                .build();
    }


    @Test
    void shouldUpdateAnExistingProduct() throws Exception{
        // given
        var product = createProductRequest("Produto de teste", 6);
        var body = objectMapper.writeValueAsString(product);

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/products/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenProductDoesNotExist() throws Exception{
        // given
        var product = createProductRequest("Produto de teste", 699);
        var body = objectMapper.writeValueAsString(product);

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/products/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}