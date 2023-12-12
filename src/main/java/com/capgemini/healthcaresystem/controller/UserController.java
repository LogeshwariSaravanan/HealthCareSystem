package com.capgemini.healthcaresystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;
import com.capgemini.healthcaresystem.exception.UserNotFoundException;
import com.capgemini.healthcaresystem.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	
//	@PostMapping("/add")
//	public UserDto addUser(@RequestBody User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException {
//		return userService.addUser(user);
//	}
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> addUser(@RequestBody User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException
	{
		return new ResponseEntity<UserDto>(userService.addUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public List<UserDto> getUser(){
		return userService.getUser();
	}
	
	
	@PostMapping("/makeappointment/{userid}/{diagnosticcenterid}/{testid}/{dateandtime}")
	public AppointmentDto makeAppointment(@PathVariable (value="userid") String userId, @PathVariable (value="diagnosticcenterid") String diagnosticCenterid, @PathVariable (value="testid") String testId, @PathVariable (value="dateandtime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dateAndTime) throws UserNotFoundException,IdNotFoundException{
		return userService.makeAppointment(userId, diagnosticCenterid, testId, dateAndTime);
	}


	@PutMapping("/approveappointment/{userid}/{diagnosticcenterId}")
	public boolean approveAppointment(@PathVariable (value="userid") String userId, @PathVariable (value="diagnosticcenterId")String diagnosticCenterId ) throws InvalidUserException,IdNotFoundException{
		return userService.approveAppointment(userId,diagnosticCenterId);
	}

}
