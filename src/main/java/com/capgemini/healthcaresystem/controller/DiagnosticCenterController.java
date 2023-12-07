package com.capgemini.healthcaresystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.service.DiagnosticCenterService;

@RestController
@RequestMapping("/api/diagnosticcenter")
public class DiagnosticCenterController {
	@Autowired
	DiagnosticCenterService diagnosticCenterService;
	
	
	@PostMapping("/add")
	public DiagnosticCenter addCenter(@RequestBody DiagnosticCenter diagnosticCenter) {
		System.out.println(diagnosticCenter);
		 diagnosticCenterService.addCenter(diagnosticCenter);
		 return diagnosticCenter;
	}
	
	@GetMapping("/get")
	public List<DiagnosticCenter> getAllDiagnosticCenter(){
		return diagnosticCenterService.getCenter();
		
	}

}
