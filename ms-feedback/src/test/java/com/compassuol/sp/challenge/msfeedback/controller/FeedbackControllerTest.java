package com.compassuol.sp.challenge.msfeedback.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Long feedbackIdToDelete;

    @BeforeEach
    void setUp() {
        feedbackIdToDelete = 1L;
    }

    @Test
    void testGetAllFeedbacksEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/feedbacks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scale").value("VERY_DISSATISFIED"))
                .andExpect(jsonPath("$[0].comment").value("this is not good"));
    }

    @Test
    void testDeleteFeedbackEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/feedbacks/" + feedbackIdToDelete))
                .andExpect(status().isOk());
    }
}
