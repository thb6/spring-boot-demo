package com.sample.talha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Users> getUsers() {
    	return userService.getUsers();
    }
    
   // @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/secured/users")
    public List<Users> createUser(@RequestBody Users user) {
    	userService.save(user);
    	return userService.getUsers();
    }
    
 // @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/users/{id}")
    public Users getUserById(@PathVariable("id")int id) {
    	return userService.getUserById(id);
    }
    
    @RequestMapping(value = "/secured/users/", params="email", method = RequestMethod.GET)
    public Users getUserByEmail(@RequestParam("email") String email) {
    	return userService.getUserByEmail(email);
    }
    
    @PutMapping("/secured/users")
    public void updateUserByEmail(@RequestBody Users updatedUser) {
    	userService.updateUserByEmail(updatedUser);
    }
 // @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/secured/users/{id}")
    public void deleteUserById(@PathVariable("id")int id) {
    	 userService.deleteUserById(id);
    }
    
    
    /*
     * Polygons begin
     */

    @GetMapping("/secured/polygon/{id}")
    public Polygon getPolygonById(@PathVariable("id")int id) {
    	return polygonService.getPolygonById(id);
    }
    
    @PostMapping("/secured/polygon")
    public void createPolygon(@RequestBody Polygon polygon) {
    	polygonService.save(polygon);
    }
    
    @GetMapping("secured/polygon/{polygonId}/{pointId}")
    public boolean doesContain(@PathVariable("polygonId") int polygonId, @PathVariable("pointId") int pointId) {
    	return polygonService.doesContain(polygonId, pointId);
    }
    
    /*
     * Points begin
     */
   
    @GetMapping("/secured/point/{id}")
    public Point getPointById(@PathVariable("id")int id) {
    	return pointService.getPointById(id);
    }
    
    @PostMapping("/secured/point")
    public void createPolygon(@RequestBody Point point) {
    	pointService.save(point);
    }
}
