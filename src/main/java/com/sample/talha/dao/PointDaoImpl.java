package com.sample.talha.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.sample.talha.entities.Point;

@Repository
public class PointDaoImpl extends JdbcDaoSupport implements PointDao {

	
    @Autowired 
    DataSource dataSource;
	
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
    
	@Override
	public void save(Point point) {
		String sql = "INSERT INTO point VALUES (nextval('dummy_pointid_seq'), ?, ST_GeomFromText(?));";

		/*
		 *  nextval() autoincrements the primary key saved in the Database
		 *  ST_GeomFromText(?) converts the String to a PostGIS Geometry object and allows it to be stored
		 */
		 getJdbcTemplate().update(sql, new Object[]{
				 point.getName(),
				 point.getGeom()
		 });
	}

	@Override
	public Point getPointById(int id) {
		String sql = "SELECT point_id, name, ST_AsText(geom) FROM point WHERE point_id = ?";
		
		return (Point)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Point>(){
			
			//Create empty Point object and assign it the fields from the point with the specified ID
			@Override
			public Point mapRow(ResultSet rs, int rowNum) throws SQLException {
				Point point = new Point();
				point.setId(rs.getInt("point_id"));
				point.setName(rs.getString("name"));
				point.setGeom(rs.getString("st_astext"));
		        return point;
				
			}
		});
	}

}
