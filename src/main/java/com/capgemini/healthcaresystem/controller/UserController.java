package com.capgemini.healthcaresystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidDateException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;
import com.capgemini.healthcaresystem.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public UserDto addUser(@RequestBody User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException, IdAlreadyExistException{
		return userService.addUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) throws IdNotFoundException,InvalidPasswordException{
		return userService.login(user);
	}
	
	@GetMapping("/get")
	public List<UserDto> getUser(){
		return userService.getUser();
	}
	
	@PostMapping("/makeappointment")
	public Appointment makeAppointment(@RequestBody Appointment appointment) throws IdNotFoundException, IdAlreadyExistException,InvalidDateException {
		return userService.makeAppointment(appointment);
	}

	@PutMapping("/approveappointment/{userid}/{diagnosticcenterId}")
	public String approveAppointment(@PathVariable (value="userid") String userId, @PathVariable (value="diagnosticcenterId")String diagnosticCenterId ) throws InvalidUserException,IdNotFoundException{
		return userService.approveAppointment(userId,diagnosticCenterId);
	}
	
	@GetMapping("/checkstatus/{appointmentid}")
	public String checkStatus(@PathVariable (value="appointmentid") int appointmentId) throws IdNotFoundException{
		return userService.checkStatus(appointmentId);
	}
	
	@DeleteMapping("/cancelappointment/{appointmentid}")
	public String cancelAppointment(@PathVariable (value="appointmentid") int appointmentId) throws IdNotFoundException{
		return userService.cancelAppointment(appointmentId);
	}
	

}
