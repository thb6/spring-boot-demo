package com.sample.talha.service;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.talha.dao.PointDao;
import com.sample.talha.dao.PolygonDao;
import com.sample.talha.entities.Point;
import com.sample.talha.entities.Polygon;

@Service
public class PolygonServiceImpl implements PolygonService {

	@Autowired
	PolygonDao polygonDao;
	
	@Autowired
	PointDao pointDao;
	
	GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
	
	
	WKTReader reader = new WKTReader(geometryFactory);
	
	@Override
	@Transactional
	public void save(Polygon polygon) {
		polygonDao.save(polygon);
	}

	@Override
	@Transactional
	public Polygon getPolygonById(int id) {
		return polygonDao.getPolygonById(id);
	}

	@Override
	public boolean doesContain(int polygonId, int pointId) {
		Polygon polygon = polygonDao.getPolygonById(polygonId);
		Point point = pointDao.getPointById(pointId);
		try {
			Geometry comparePoint = reader.read(point.getGeom());
			Geometry comparePolygon = reader.read(polygon.getGeom());
			return comparePolygon.contains(comparePoint);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
		 
	}

}
