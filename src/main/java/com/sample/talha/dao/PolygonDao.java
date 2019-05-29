package com.sample.talha.dao;

import com.sample.talha.entities.Polygon;

public interface PolygonDao {

	public void save(Polygon polygon);
	
	public Polygon getPolygonById(int id);
}
