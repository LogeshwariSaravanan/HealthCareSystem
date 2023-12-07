package com.capgemini.healthcaresystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User addUser(User user) {
		
		return userRepository.save(user);
	}

	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
