package com.hexagonalexample.adapter.in.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetAllProductsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public GetAllProductsControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldReturnAllProducts() throws Exception{
        // given

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products/get")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpectAll(jsonPath("$.[:1].id").isNotEmpty(), jsonPath("$.[:1].name").isNotEmpty(),
                        jsonPath("$.[:1].quantity").isNotEmpty(), jsonPath("$.[:1].price").isNotEmpty());
    }
}