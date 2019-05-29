package com.sample.talha.config;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	public GeometryFactory geometryFactory() {
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
		return geometryFactory;
	}
	
	@Bean
	public WKTReader reader() {
        WKTReader reader = new WKTReader();
        return reader;
	}
}
