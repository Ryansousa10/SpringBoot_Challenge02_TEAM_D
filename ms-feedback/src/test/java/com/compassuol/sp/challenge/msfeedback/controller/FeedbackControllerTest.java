package com.compassuol.sp.challenge.msfeedback.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllFeedbacksEndpoint() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/feedbacks"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isNotEmpty();
        assertThat(content).contains("\"scale\":\"VERY_DISSATISFIED\"");
        assertThat(content).contains("\"comment\":\"this is not good\"");
    }
}
