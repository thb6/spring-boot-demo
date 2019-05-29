package com.sample.talha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sample.talha.filters.RequestResponseLoggingFilter;

@SpringBootApplication
public class TalhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalhaApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
	    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	         
	    registrationBean.setFilter(new RequestResponseLoggingFilter());
	    registrationBean.addUrlPatterns("/rest/secured/*");
	         
	    return registrationBean;    
	}
	

}
