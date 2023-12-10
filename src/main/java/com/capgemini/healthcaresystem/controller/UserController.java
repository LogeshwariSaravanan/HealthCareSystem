package com.capgemini.healthcaresystem.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	@GetMapping("/get")
	public List<User> getUser(){
		return userService.getUser();
	}
	
	@PostMapping("/makeappointment/{userid}/{diagnosticcenterid}/{testid}/{dateandtime}")
	public AppointmentDto makeAppointment(@PathVariable (value="userid") String userId, @PathVariable (value="diagnosticcenterid") String diagnosticCenterid, @PathVariable (value="testid") String testId, @PathVariable (value="dateandtime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dateAndTime) {
		return userService.makeAppointment(userId, diagnosticCenterid, testId, dateAndTime);
	}
	
	@PutMapping("/approveappointment/{userid}/{appointmentid}")
	public boolean approveAppointment(@PathVariable (value="userid") String userId, @PathVariable (value="appointmentid") BigInteger appointmentId ) {
		return userService.approveAppointment(userId,appointmentId);
	}

}
