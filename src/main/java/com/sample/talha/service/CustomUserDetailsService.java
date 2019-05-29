package com.sample.talha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.talha.entities.CustomUserDetails;
import com.sample.talha.entities.Users;
import com.sample.talha.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


	

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 //       Optional<Users> optionalUsers = userRepository.findByName(username);
    	Users users = userRepository.findByName(username);

//        optionalUsers
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return optionalUsers
//                .map(CustomUserDetails::new).get();
    	return new CustomUserDetails(users);
    }
}
