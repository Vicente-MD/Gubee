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
class DeleteProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public DeleteProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldDeleteProductWhenIdExists() throws Exception{
        // given
        var id = 7;

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/products/delete/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnBadRequestWhenIdDoesNotExists() throws Exception{
        // given
        var id = 599;

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/products/delete/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}