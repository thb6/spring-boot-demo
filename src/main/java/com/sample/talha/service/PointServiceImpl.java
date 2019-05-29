package com.sample.talha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.talha.dao.PointDao;
import com.sample.talha.entities.Point;

@Service
public class PointServiceImpl implements PointService {

	@Autowired
	PointDao pointDao;
	
	@Override
	@Transactional
	public void save(Point point) {
		pointDao.save(point);
	}

	@Override
	@Transactional
	public Point getPointById(int id) {
		return pointDao.getPointById(id);
	}

}
