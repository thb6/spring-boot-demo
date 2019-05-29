package com.sample.talha.service;

import com.sample.talha.entities.Point;

public interface PointService {

	public void save(Point point);
	
	public Point getPointById(int id);
}
