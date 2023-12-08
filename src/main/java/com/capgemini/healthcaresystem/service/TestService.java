package com.capgemini.healthcaresystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
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
	
	
	@Autowired
	ModelMapper modelMapper;
	
		
//	public void addTest(Test test) {
//		
//		DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(test.getDiagnosticCenter().getCenterId()).get();
//		test.setDiagnosticCenter(diagnosticCenter);
//		 testRepository.save(test); 
//		 
//		 CenterTestMapping centerTestMapping=new CenterTestMapping();
//			centerTestMapping.setTestid(test.getTestid());
//			centerTestMapping.setCenterId(test.getDiagnosticCenter().getCenterId());
//			centerTestMappingRepository.save(centerTestMapping);
//	}

	
	public List<Test> getTest(){
		return testRepository.findAll();
	}

	
	public Test getTestById(String id) throws IdNotFoundException
	{
		  Optional<Test> optionalTest = testRepository.findById(id);
		  if(optionalTest.isEmpty())
			  throw new IdNotFoundException("no id present to get the test");
		 
		 return optionalTest.get();
	}





	public TestDto addTest(TestDto testDto) {
//		Test test=new Test();
//		test.setTestid(testDto.getTestid());
//		test.setTestName(testDto.getTestName());
		
		Test test = modelMapper.map(testDto, Test.class);
		
		testRepository.save(test);
	
		TestDto testDto2 = modelMapper.map(test, TestDto.class);
		
		return testDto2;
	}






	
	

}
