package com.sample.talha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.talha.endpoints.geometry.GeometryIntegrationSuiteIT;
import com.sample.talha.endpoints.user.UserIntegrationSuiteIT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UserIntegrationSuiteIT.class)
public class TalhaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
