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
	
	
	@PostMapping("/add")
	public DiagnosticCenterDto addCenter(@RequestBody DiagnosticCenterDto diagnosticCenterDto) {
//		System.out.println(diagnosticCenter);
		return diagnosticCenterService.addCenter(diagnosticCenterDto);
		 
	}
	
	@PostMapping("/add/{cid}/{tid}")
	public DiagnosticCenterDto addTestToCenter(@PathVariable (value="cid") String centerId, @PathVariable (value="tid") String testId ) {
		return diagnosticCenterService.addTest(centerId,testId);
	}
	
//	@GetMapping("/get")
//	public List<DiagnosticCenter> getAllDiagnosticCenter(){
//		return diagnosticCenterService.getCenter();
//		
//	}

}
