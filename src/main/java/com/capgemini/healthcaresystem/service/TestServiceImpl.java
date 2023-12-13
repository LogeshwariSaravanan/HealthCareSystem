package com.capgemini.healthcaresystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.repository.CenterTestMappingRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;
import com.capgemini.healthcaresystem.repository.UserRepository;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	CenterTestMappingRepository centerTestMappingRepository;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	ModelMapper modelMapper;
	public TestDto addTest(String userId,Test test)throws InvalidUserException,IdAlreadyExistException, IdNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		  if(optionalUser.isEmpty())
			  throw new IdNotFoundException("user not found");
		 User user=optionalUser.get();
		  if(user.getUserRole().equals("Admin")) {
			  List<String> listOfTest=testRepository.listOfTestId();
			  if(!listOfTest.contains(test.getTestid())) {
				  testRepository.save(test);
				  TestDto testDto2 = modelMapper.map(test, TestDto.class);
				  return testDto2;
			  }
			  else {
				  throw new IdAlreadyExistException("test already present");
			  }
		
		  }
		  else {
			  throw new InvalidUserException("Admin only add test");
		  }
	}
	
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





	






	
	

}
