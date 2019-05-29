package com.sample.talha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.sample.talha.entities.Users;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Autowired 
    DataSource dataSource;
	
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
    
	@Override
	public List<Users> getUsers() {
        String sql = "SELECT * FROM users";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        
        List<Users> result = new ArrayList<Users>();
        
        // for each row, read data from columns and assign to appropriate fields
        for(Map<String, Object> row:rows){
          Users user = new Users();
          user.setId((Integer)row.get("user_id"));
          user.setName((String)row.get("name"));
          user.setLastName((String) row.get("last_name"));
          user.setActive((Integer) row.get("active"));
          user.setEmail((String) row.get("email"));
          result.add(user);
        }
        
        return result;
	}

	@Override
	public void save(Users user) {
		
	     String saveUser = "INSERT INTO users " +
		 "(name, last_name, active, email, password) VALUES (?, ?, ?, ?, ?)" ;
		     getJdbcTemplate().update(saveUser, new Object[]{
		    		 user.getName(),
		    		 user.getLastName(),
		    		 user.getActive(),
		    		 user.getEmail(),
		    		 user.getPassword()
		 });
		     
		     
		 /*
		  * Get ID of Last Created User
		  * Should be using KeyHolder but not working as of now    
		  */
		 List<Users> userList = getUsers();
		 int key = userList.get(userList.size()-1).getId();
		 
		 /*
		  * Give created User default ROLE of USER
		  * Later could add functionality for Admin to assign other Admin ROLES
		  */
		 String saveUserRole = "INSERT INTO user_role (user_id, role_id) VALUES(?, ?)";
		 getJdbcTemplate().update(saveUserRole, new Object[]{
	    		 key, 2
	 });
	}


	@Override
	public Users getUserById(int id) {
	String sql = "SELECT * FROM users WHERE user_id = ?";
	return (Users)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Users>(){
		
		//Create empty User object and assign it the fields from the user with the specified ID
		@Override
		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users user = new Users();
			user.setId(rs.getInt("user_id"));
	          user.setName(rs.getString("name"));
	          user.setLastName( rs.getString("last_name"));
	          user.setActive(rs.getInt("active"));
	          user.setEmail( rs.getString("email"));
	          return user;
			
		}
	});
	}

	@Override
	public void deleteUserById(int id) {
		String deleteUserRole = "DELETE FROM user_role WHERE user_id = ?";
		Object[] deleteUserRoleArgs = new Object[] {id};
	    getJdbcTemplate().update(deleteUserRole, deleteUserRoleArgs);

		String deleteUser = "DELETE FROM users WHERE user_id = ?";
		Object[] deleteUserArgs = new Object[] {id};
	    getJdbcTemplate().update(deleteUser, deleteUserArgs);

	}

	@Override
	public Users getUserByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		return (Users)getJdbcTemplate().queryForObject(sql, new Object[]{email}, new RowMapper<Users>(){
			
			//Create empty User object and assign it the fields from the user with the specified ID
			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users user = new Users();
				user.setId(rs.getInt("user_id"));
		          user.setName(rs.getString("name"));
		          user.setLastName( rs.getString("last_name"));
		          user.setActive(rs.getInt("active"));
		          user.setEmail( rs.getString("email"));
		          return user;
				
			}
		});
	}

	@Override
	public void updateUserByEmail(Users updatedUser) {		
		String sql = "UPDATE users SET name = ?, last_name = ?, active = ?, email = ?, password = ?  WHERE user_id = ?";
		
		getJdbcTemplate().update(sql, new Object[]{
				updatedUser.getName(),
				updatedUser.getLastName(),
				updatedUser.getActive(),
				updatedUser.getEmail(),
				updatedUser.getPassword(),
				updatedUser.getId()
		
		});
	}



}
