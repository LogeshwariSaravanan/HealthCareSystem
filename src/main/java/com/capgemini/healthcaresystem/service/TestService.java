package com.capgemini.healthcaresystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.repository.CenterTestMappingRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;

@Service
public class TestService {
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	CenterTestMappingRepository centerTestMappingRepository;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;
	
	public void addTest(Test test) {
		
		DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(test.getDiagnosticCenter().getCenterId()).get();
		test.setDiagnosticCenter(diagnosticCenter);
		 testRepository.save(test); 
		 
		 CenterTestMapping centerTestMapping=new CenterTestMapping();
			centerTestMapping.setTestid(test.getTestid());
			centerTestMapping.setCenterId(test.getDiagnosticCenter().getCenterId());
			centerTestMappingRepository.save(centerTestMapping);
	}
	
	public List<Test> getTest(){
		return testRepository.findAll();
	}

}
