package com.capgemini.healthcaresystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.UserNotFoundException;
import com.capgemini.healthcaresystem.repository.CenterTestMappingRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;
import com.capgemini.healthcaresystem.repository.UserRepository;


@Service
public class DiagnosticCenterServiceImpl implements DiagnosticCenterService {
	@Autowired 
	DiagnosticCenterRepository diagnosticCenterRepository;
	@Autowired
	TestRepository testRepository;
	@Autowired
	CenterTestMappingRepository centerTestMappingRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	
	private static List<TestDto> testDtos=new ArrayList();
	static {
		testDtos.add(new TestDto("bt","Blood Test"));
		testDtos.add(new TestDto("st","Sugar Test"));
		testDtos.add(new TestDto("bp","Blood Pressure"));
		
	}
	
	
	@Override
	public DiagnosticCenterDto addCenter(String userId, DiagnosticCenter diagnosticCenter) throws UserNotFoundException, InvalidUserException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new UserNotFoundException("User not found");
		}
		User user = userOptional.get();
		if(user.getUserRole().equals("Admin")) {
			diagnosticCenterRepository.save(diagnosticCenter);
			DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
			diagnosticCenterDto2.setListOfTest(testDtos);
			for(int i=0;i<3;i++) {
				CenterTestMapping centerTestMapping=new CenterTestMapping();
			    centerTestMapping.setTestid(diagnosticCenterDto2.getListOfTest().get(i).getTestid());
			    centerTestMapping.setCenterId(diagnosticCenterDto2.getCenterId());
			    centerTestMappingRepository.save(centerTestMapping);
			}
			
			return diagnosticCenterDto2;
		}
		else {
			throw new InvalidUserException("Admin only add the center");
		}
	}
	
	
	public DiagnosticCenterDto addTest(String userId,String centerId, String testId)throws UserNotFoundException,InvalidUserException,IdAlreadyExistException, IdNotFoundException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new UserNotFoundException("User not found");
		}
		User user=userRepository.findById(userId).get();
		if(user.getUserRole().equals("Admin")) {
			
			List<String> listOfTestId=centerTestMappingRepository.findTestIdByCenterId(centerId);
			List<String> listOfTest=testRepository.listOfTestId();
			if(listOfTest.contains(testId)) {
				if(!listOfTestId.contains(testId)) {
					
				    CenterTestMapping centerTestMapping=new CenterTestMapping();
					centerTestMapping.setTestid(testId);
					centerTestMapping.setCenterId(centerId);
					centerTestMappingRepository.save(centerTestMapping);
					
					DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(centerId).get();
					DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
					
				 	List<TestDto> listOfTestDtos=new ArrayList<>();
				    List<CenterTestMapping> centerTestMappings=	centerTestMappingRepository.findBycenterId(centerId);
				    for(CenterTestMapping ctm:centerTestMappings) {
					    Test test=testRepository.findById(ctm.getTestid()).get();
					    TestDto testDto=modelMapper.map(test,TestDto.class);
					    listOfTestDtos.add(testDto);
					    }
				    diagnosticCenterDto2.setListOfTest(listOfTestDtos);
				  
				 	return diagnosticCenterDto2;
				 	}
					else {
						throw new IdAlreadyExistException("Test already exist in this center");
					}
			}
			else {
				throw new IdNotFoundException("Test not present");
			}	
			
		}
		else {
			throw new InvalidUserException("Admin only add the center");

		}
				
	}


	@Override
	public boolean deleteCenter(String userId, String diagnosticCenterId) {
		User user=userRepository.findById(userId).get();
		if(user.getUserRole().equals("Admin")) {
			diagnosticCenterRepository.deleteById(diagnosticCenterId);
			centerTestMappingRepository.deleteCenterTestMappingById(diagnosticCenterId);
//			centerTestMappingRepository.deleteBycenterId(diagnosticCenterId);
			return true;
		}
		else {
		return false;
		}
	}


	@Override
	public boolean deleteTest(String userId, String diagnosticCenterId, String testId) {
		User user=userRepository.findById(userId).get();
		if(user.getUserRole().equals("Admin")) {
			centerTestMappingRepository.deletebyCenterIdAndTestId(diagnosticCenterId, testId);
			return true;
		}
		else {
		return false;
		}
	}


	


	

	}
