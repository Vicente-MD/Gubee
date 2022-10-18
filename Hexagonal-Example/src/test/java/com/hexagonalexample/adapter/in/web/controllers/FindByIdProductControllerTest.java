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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FindByIdProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public FindByIdProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldFindAProductWhenIdExists() throws Exception{
        // given
        var id = 6;

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/products/get/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpectAll(jsonPath("$.id").value(id), jsonPath("$.name").isNotEmpty(),
                        jsonPath("$.quantity").isNotEmpty(), jsonPath("$.price").isNotEmpty());
    }

    @Test
    void shouldReturnBadRequestWhenIdDoesNotExist() throws Exception{
        // given
        var id = 699;

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/products/get/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}