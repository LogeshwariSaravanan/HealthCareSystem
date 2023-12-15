package com.capgemini.healthcaresystem.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.Tests;
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
	
	
	
	
	public TestDto addTest(String userId,Tests test)throws InvalidUserException,IdAlreadyExistException, IdNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		  if(optionalUser.isEmpty())
			  throw new IdNotFoundException("user not found");
		 User user=optionalUser.get();
		  if(user.getUserRole().equals("Admin")) {
			  List<String> listOfTestName=testRepository.findTest();
			  if(!listOfTestName.contains(test.getTestName())) {
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
	
	public List<Tests> getTest(){
		
		List<Tests> tests = testRepository.findAll();
		System.out.println(tests);
		
		return tests;
	}

	
	public Tests getTestById(String id) throws IdNotFoundException
	{
		  Optional<Tests> optionalTest = testRepository.findById(id);
		  if(optionalTest.isEmpty())
			  throw new IdNotFoundException("no id present to get the test");
		 
		 return optionalTest.get();
	}

	@Override
	public String deleteTest(String userId, String testId) throws IdNotFoundException, InvalidUserException {
		if(userRepository.existsById(userId)) {
			User user=userRepository.findById(userId).get();
			if(user.getUserRole().equals("Admin")) {
				if(testRepository.existsById(testId)) {
					Tests test=testRepository.findById(testId).get();
					centerTestMappingRepository.deleteCenterByTest(test);
					testRepository.deleteById(testId);
					return test.getTestName()+" is deleted successfully";

				}
				else {
					throw new IdNotFoundException("Test not found");
				}
			}
			else {
				throw new InvalidUserException("Admin only permitted to delete the center");
			}
		}
		else {
			throw new IdNotFoundException("User not found");
		}
	}

//	@Override
//	public String deleteTest(String testId) throws IdNotFoundException {
//		if(testRepository.existsById(testId)) {
//			testRepository.deleteById(testId);
//			List<CenterTestMapping> listOfCenterTestMapping=centerTestMappingRepository.findBytestid(testId);
//			for(CenterTestMapping ctm:listOfCenterTestMapping) {
//				centerTestMappingRepository.deleteById(ctm.getTcId());
//			}
//			return "test id : "+testId+" deleted successfully";
//		}
//		throw new IdNotFoundException("Test not Found");
//	}
//
//
//
//

	






	
	

}
