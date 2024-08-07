package com.fleetmanagement.api_rest;

import com.fleetmanagement.api_rest.model.TrajectoryModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class ApiRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("All taxis with pagination")
	void contextLoadsParams() throws Exception {
		Integer lengthResult = 4;
		mockMvc.perform(get("/taxis")
						.param("limit","4")
						.param("page","1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(lengthResult));
	}
	@Test
	@DisplayName("Search Trajectories by taxi and plate")
	void trajectoriesFilteredByTaxiId() throws Exception {
		String taxiId = "7249";
		String date = "05-02-2008";
		mockMvc.perform(get("/trajectories")
						.param("taxiId",taxiId)
						.param("date",date))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers
						.content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].taxiId").value(taxiId));
	}
}

