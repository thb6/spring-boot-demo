package com.sample.talha.endpoints.geometry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sample.talha.geometry.integrationtest.GetRequestPoint;
import com.sample.talha.geometry.integrationtest.GetRequestPolygon;
import com.sample.talha.geometry.integrationtest.PolygonDoesContainPoint;
import com.sample.talha.geometry.integrationtest.PostRequestPoint;
import com.sample.talha.geometry.integrationtest.PostRequestPolygon;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PostRequestPolygon.class,
	GetRequestPolygon.class,
	PostRequestPoint.class,
	GetRequestPoint.class,
	PolygonDoesContainPoint.class
	})
public class GeometryIntegrationSuiteIT {

}
