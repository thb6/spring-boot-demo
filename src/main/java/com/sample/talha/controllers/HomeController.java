package com.sample.talha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.talha.entities.Point;
import com.sample.talha.entities.Polygon;
import com.sample.talha.entities.Users;
import com.sample.talha.response.CustomAdminResponse;
import com.sample.talha.service.PointService;
import com.sample.talha.service.PolygonService;
import com.sample.talha.service.UserService;


@RestController
@RequestMapping("/rest")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PolygonService polygonService;
	
	@Autowired
	private PointService pointService;
	
	
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/users")
    public ResponseEntity getUsers() {
    	return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
    
   // @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/secured/users")
    public CustomAdminResponse createUser(@RequestBody Users user) {
    	if(user == null) {
        	return new CustomAdminResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "User is null");
    	}
    	userService.save(user);
    	return new CustomAdminResponse(HttpStatus.OK.value(), HttpStatus.OK, "User Created Successfully");
    }
    
 // @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/users/{id}")
    public ResponseEntity getUserById(@PathVariable("id")int id) {
    	
    	Users user = userService.getUserById(id);
    	if(user != null) {
        	return new ResponseEntity<>(user, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<> ("No user with that id exists", HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(value = "/secured/users/", params="email", method = RequestMethod.GET)
    public ResponseEntity getUserByEmail(@RequestParam("email") String email) {
    	
    	Users user = userService.getUserByEmail(email);
    	if(user != null) {
    	return new ResponseEntity<>(user, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<> ("No user with that email exists", HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/secured/users")
    public CustomAdminResponse updateUserByEmail(@RequestBody Users updatedUser) {
    	if(updatedUser.getId() < 1) {
       	 	
    		return new CustomAdminResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "User id not present");

    	}
    	userService.updateUserByEmail(updatedUser);
   	 	return new CustomAdminResponse(HttpStatus.OK.value(), HttpStatus.OK, "User Updated Successfully");

    	
    }
 // @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/secured/users/{id}")
    public CustomAdminResponse deleteUserById(@PathVariable("id")int id) {
    	 userService.deleteUserById(id);
    	 return new CustomAdminResponse(HttpStatus.OK.value(), HttpStatus.OK, "User Deleted Successfully");
    }
    
    
    /*
     * Polygons begin
     */

    @GetMapping("/secured/polygon/{id}")
    public ResponseEntity getPolygonById(@PathVariable("id")int id) {
    	Polygon polygon = polygonService.getPolygonById(id);
    	if(polygon == null) {
    		return new ResponseEntity<>("No Polygon By That ID", HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>(polygon, HttpStatus.OK);
    }
    
    @PostMapping("/secured/polygon")
    public CustomAdminResponse createPolygon(@RequestBody Polygon polygon) {
    	if(polygon == null) {
    		return new CustomAdminResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "You did not provide a polygon");
    	}
    	polygonService.save(polygon);
    	return new CustomAdminResponse(HttpStatus.OK.value(), HttpStatus.OK, "Polygon Created Successfully");
    }
    
    @GetMapping("secured/polygon/{polygonId}/{pointId}")
    public ResponseEntity doesContain(@PathVariable("polygonId") int polygonId, @PathVariable("pointId") int pointId) {
    	return new ResponseEntity<>(polygonService.doesContain(polygonId, pointId), HttpStatus.OK);
    }
    
    /*
     * Points begin
     */
   
    @GetMapping("/secured/point/{id}")
    public ResponseEntity getPointById(@PathVariable("id")int id) {
    	Point point =  pointService.getPointById(id);
    	if(point == null) {
    		return new ResponseEntity<>("No point By That ID", HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>(point, HttpStatus.OK);
    }
    
    @PostMapping("/secured/point")
    public CustomAdminResponse createPolygon(@RequestBody Point point) {
    	if(point == null) {
    		return new CustomAdminResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "You did not provide a point");
    	}
    	pointService.save(point);
    	return new CustomAdminResponse(HttpStatus.OK.value(), HttpStatus.OK, "Point Created Successfully");

    	
    }
}
