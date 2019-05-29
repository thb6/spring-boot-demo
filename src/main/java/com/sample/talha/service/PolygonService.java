package com.sample.talha.service;

import com.sample.talha.entities.Point;
import com.sample.talha.entities.Polygon;

public interface PolygonService {
	
	public void save(Polygon polygon);
	
	public Polygon getPolygonById(int id);
	
	public boolean doesContain(int polygonId, int pointId);
	
	// public Boolean contains()
}
