package com.sample.talha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.sample.talha.entities.Polygon;

@Repository
public class PolygonDaoImpl extends JdbcDaoSupport implements PolygonDao {

	
    @Autowired 
    DataSource dataSource;
	
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
    
	@Override
	public void save(Polygon polygon) {
		
		/*
		 *  nextval() autoincrements the primary key saved in the Database
		 *  ST_GeomFromText(?) converts the String to a PostGIS Geometry object and allows it to be stored
		 */
		String sql = "INSERT INTO polygon VALUES (nextval('dummy_id_seq'), ?, ST_GeomFromText(?));";
		getJdbcTemplate().update(sql, new Object[]{
				 polygon.getName(),
				 polygon.getGeom()
		});
	     
	}

	@Override
	public Polygon getPolygonById(int id) {
		
		String sql = "SELECT polygon_id, name, ST_AsText(geom) FROM polygon WHERE polygon_id = ?";
		
		return (Polygon)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Polygon>(){
			
			//Create empty Polygon object and assign it the fields from the polygon with the specified ID
			@Override
			public Polygon mapRow(ResultSet rs, int rowNum) throws SQLException {
				Polygon polygon = new Polygon();
				polygon.setId(rs.getInt("polygon_id"));
				polygon.setName(rs.getString("name"));
		        polygon.setGeom(rs.getString("st_astext"));
		        return polygon;
				
			}
		});
	}

}
