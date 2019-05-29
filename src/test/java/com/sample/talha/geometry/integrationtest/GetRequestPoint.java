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

import com.sample.talha.TalhaApplication;
import com.sample.talha.entities.Point;
import com.sample.talha.service.PointService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TalhaApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetRequestPoint {

	@Autowired
	private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @Autowired
    PointService pointService;
    
    @Before
    public void setup() throws Exception {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    @WithMockUser(username = "jwick3", password = "password", roles = "ADMIN")
    public void test1_getPolygonByID() throws Exception {
    	Point point = pointService.getPointById(1);
    	mockMvc.perform(MockMvcRequestBuilders.get("/rest/secured/point/" + point.getId()))
    	.andExpect(MockMvcResultMatchers.status().is(200))
    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    	.andExpect(MockMvcResultMatchers.jsonPath("$." + "id").value(point.getId()))
    	.andExpect(MockMvcResultMatchers.jsonPath("$." + "geom").value(point.getGeom()));

    }
}
