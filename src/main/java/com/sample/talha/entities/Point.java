package com.sample.talha.entities;

import org.springframework.stereotype.Component;

@Component
public class Point {

		private int id;
		private String name;
		private String geom;
		
		
		public Point() {
			
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getGeom() {
			return geom;
		}


		public void setGeom(String geom) {
			this.geom = geom;
		}

}
