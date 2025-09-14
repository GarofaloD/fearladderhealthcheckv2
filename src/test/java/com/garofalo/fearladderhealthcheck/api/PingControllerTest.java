package com.garofalo.fearladderhealthcheck.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(PingController.class)
public class PingControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BuildProperties buildProperties;

    void ping_shouldReturnPong() throws Exception{

        when(buildProperties.getVersion()).thenReturn("0.0.1-SNAPSHOT");
        mockMvc.perform(get("/api/v1/ping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("0.0.1-SNAPSHOT"))
                .andExpect(jsonPath("$.status").value("pong"));

    }






}
