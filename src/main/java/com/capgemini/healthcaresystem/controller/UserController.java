package com.capgemini.healthcaresystem.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;
import com.capgemini.healthcaresystem.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> addUser(@RequestBody User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException, IdAlreadyExistException
	{
		return new ResponseEntity<UserDto>(userService.addUser(user), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(@RequestBody User user) throws IdNotFoundException,InvalidPasswordException{
		return new ResponseEntity<String>(userService.login(user),HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public List<UserDto> getUser(){
		return userService.getUser();
	}
	
	@PostMapping("/makeappointment")
	public AppointmentDto makeAppointment(@RequestBody Appointment appointment) throws IdNotFoundException, IdAlreadyExistException {
		return userService.makeAppointment(appointment);
	}


	@PutMapping("/approveappointment/{userid}/{diagnosticcenterId}")
	public boolean approveAppointment(@PathVariable (value="userid") String userId, @PathVariable (value="diagnosticcenterId")String diagnosticCenterId ) throws InvalidUserException,IdNotFoundException{
		return userService.approveAppointment(userId,diagnosticCenterId);
	}
	
	@GetMapping("/checkstatus/{appointmentid}")
	ResponseEntity<String> checkStatus(@PathVariable (value="appointmentid") int appointmentId) throws IdNotFoundException{
		return new ResponseEntity<String>(userService.checkStatus(appointmentId),HttpStatus.OK);
	}
	
	@DeleteMapping("/cancelappointment/{appointmentid}")
	ResponseEntity<String> cancelAppointment(@PathVariable (value="appointmentid") int appointmentId) throws IdNotFoundException{
		return new ResponseEntity<String>(userService.cancelAppointment(appointmentId),HttpStatus.OK);
	}

}
