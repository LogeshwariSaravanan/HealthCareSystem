package com.capgemini.healthcaresystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;

@Service
public class DiagnosticCenterService {
	@Autowired 
	DiagnosticCenterRepository diagnosticCenterrepo;
	@Autowired
	TestRepository testRepository;
	
	public void addCenter(DiagnosticCenter diagnosticCenter) {
		diagnosticCenterrepo.save(diagnosticCenter);
	}
	
	public List<DiagnosticCenter> getCenter(){
		return diagnosticCenterrepo.findAll();
	}
}
