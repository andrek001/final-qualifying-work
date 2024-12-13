package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.auth.JwtRequest;
import com.example.demo.exceptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Disabled
	@Test
	void testLogin() throws Exception {
		final JwtRequest loginForm = new JwtRequest();
		loginForm.setLogin("log");
		loginForm.setPassword("password");
		final String responseAsString = mockMvc.perform(post("/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(loginForm))).andExpect(status().isNotFound())
				.andReturn()
				.getResponse()
				.getContentAsString();
		ErrorResponse errorResponse = objectMapper.readValue(responseAsString, ErrorResponse.class);
		Assertions.assertEquals("Пользователь не найден", errorResponse.getMessage());

	}
}



