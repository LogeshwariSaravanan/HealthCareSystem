package com.capgemini.healthcaresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.UserNotFoundException;
import com.capgemini.healthcaresystem.service.DiagnosticCenterService;

@RestController
@RequestMapping("/api/diagnosticcenter")
public class DiagnosticCenterController {
	@Autowired
	DiagnosticCenterService diagnosticCenterService;
	
	
	@PostMapping("/add/{userid}")
	public DiagnosticCenterDto addCenter(@PathVariable (value="userid") String userId,@RequestBody DiagnosticCenter diagnosticCenter)throws UserNotFoundException,InvalidUserException {
		return diagnosticCenterService.addCenter(userId,diagnosticCenter);
		 
	}
	
	@DeleteMapping("/deletecenter/{userid}/{cid}")
	public boolean deleteCenter(@PathVariable (value="userid") String userId, @PathVariable (value="cid") String diagnosticCenterId) {
		return diagnosticCenterService.deleteCenter(userId,diagnosticCenterId);
	}
	
	@PostMapping("/addtest/{userid}/{cid}/{tid}")
	public DiagnosticCenterDto addTestToCenter(@PathVariable (value="userid") String userId, @PathVariable (value="cid") String diagnosticCenterId, @PathVariable (value="tid") String testId )throws UserNotFoundException,InvalidUserException ,IdAlreadyExistException, IdNotFoundException{
		return diagnosticCenterService.addTest(userId,diagnosticCenterId,testId);
	}
	
	@DeleteMapping("/deletetest/{userid}/{cid}/{tid}")
	public boolean deleteTest(@PathVariable (value="userid") String userId, @PathVariable (value="cid") String diagnosticCenterId, @PathVariable (value="tid") String testId) {
		return diagnosticCenterService.deleteTest(userId,diagnosticCenterId,testId);
	}
	

}
