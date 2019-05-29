package com.sample.talha.geometry.integrationtest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.talha.TalhaApplication;
import com.sample.talha.entities.Point;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TalhaApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostRequestPoint {

	 @Autowired
		private WebApplicationContext context;
	    
	    private MockMvc mockMvc;
	  
	    private final ObjectMapper mapper = new ObjectMapper();
	    
	    @Before
	    public void setup() throws Exception {
	    	this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	    }
	    
	    @Test
	    @WithMockUser(username = "jwick3", password = "password", roles = "ADMIN")
	    public void test1_createPoint() throws Exception {
	    	Point point = new Point();
	    	point.setName("testPoint");
	    	point.setGeom("POINT(0 0)");
	    	
	    	String PointJson = mapper.writeValueAsString(point);

	    	mockMvc.perform(MockMvcRequestBuilders.post("/rest/secured/point")
	    			.content(PointJson)
	    			.contentType(MediaType.APPLICATION_JSON)
	    	)
	    			.andExpect(MockMvcResultMatchers.status().is(200));
	    }
}
