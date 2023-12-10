package com.capgemini.healthcaresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.service.DiagnosticCenterService;

@RestController
@RequestMapping("/api/diagnosticcenter")
public class DiagnosticCenterController {
	@Autowired
	DiagnosticCenterService diagnosticCenterService;
	
	
	@PostMapping("/add/{userid}")
	public DiagnosticCenterDto addCenter(@PathVariable (value="userid") String userId,@RequestBody DiagnosticCenter diagnosticCenter) {
		return diagnosticCenterService.addCenter(userId,diagnosticCenter);
		 
	}
	
	@PostMapping("/addtest/{userid}/{cid}/{tid}")
	public DiagnosticCenterDto addTestToCenter(@PathVariable (value="userid") String userId, @PathVariable (value="cid") String centerId, @PathVariable (value="tid") String testId ) {
		return diagnosticCenterService.addTest(userId,centerId,testId);
	}
	

}
