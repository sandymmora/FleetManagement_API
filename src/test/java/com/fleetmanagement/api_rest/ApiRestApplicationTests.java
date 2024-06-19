package com.fleetmanagement.api_rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class ApiRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Listado Taxis sin parámetros")
	void contextLoads() throws Exception {
		mockMvc.perform(get("/taxis"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}
	@Test
	@DisplayName("Listado Taxis con parámetros")
	void contextLoadsParams() throws Exception {
		mockMvc.perform(get("/taxis")
						.param("limit","2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}
}

