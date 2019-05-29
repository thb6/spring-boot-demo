package com.sample.talha.dao;

import com.sample.talha.entities.Point;

public interface PointDao {
	
	public void save(Point point);
	
	public Point getPointById(int id);
}
