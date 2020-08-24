package com.java.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.model.UserLoginInfoModel;
import com.java.service.UserLoginInfoService;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginInforControllerTest {
	@MockBean 
	UserLoginInfoService service;
	MockMvc mvc;
	@InjectMocks
	UserLoginController controller = new UserLoginController();
	
	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetUserLoginInfo() throws Exception {
		UserLoginInfoModel user = new UserLoginInfoModel();
		user.setUsername("userA");
		when(service.getUserLoginInfoByUsername("userA")).thenReturn(user);
		mvc.perform(get("/userLogin/{username}", "userA").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.username").value("userA"));
	}
	
	@Test
	public void testAddUserLoginInfo() throws Exception {
		UserLoginInfoModel user = new UserLoginInfoModel();
		user.setUsername("userA");
		when(service.addUserLoginInfo(user)).thenReturn("userA");
		String jsonUser = new ObjectMapper().writeValueAsString(user);
		mvc.perform(post("/userLogin").contentType(MediaType.APPLICATION_JSON).content(jsonUser).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
}
