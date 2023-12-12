package com.capgemini.healthcaresystem.service;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	@Override
	public DiagnosticCenterDto addCenter(String userId, DiagnosticCenter diagnosticCenter) throws UserNotFoundException, InvalidUserException, IdAlreadyExistException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new UserNotFoundException("User not found");
		}
		User user = userOptional.get();
		if(user.getUserRole().equals("Admin")) {
			List<String> listOfDiagnosticCenterId=diagnosticCenterRepository.FindListOfCenterId();
			if(!listOfDiagnosticCenterId.contains(diagnosticCenter.getCenterId())) {
				diagnosticCenterRepository.save(diagnosticCenter);
				DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
				List<Test> listOfTest=testRepository.findAll();
				TestDto[] defaultTestDto=new TestDto[3];
				for(int i=0;i<3;i++) {
					TestDto testDto=modelMapper.map(listOfTest.get(i),TestDto.class);
					defaultTestDto[i]=testDto;
				}
				diagnosticCenterDto2.setListOfTest(Arrays.asList(defaultTestDto));
				for(int i=0;i<3;i++) {
					
					CenterTestMapping centerTestMapping=new CenterTestMapping();
				    centerTestMapping.setTestid(diagnosticCenterDto2.getListOfTest().get(i).getTestid());
				    centerTestMapping.setCenterId(diagnosticCenterDto2.getCenterId());
				    centerTestMappingRepository.save(centerTestMapping);
				}
				
				return diagnosticCenterDto2;
			}
			
			else {
				throw new IdAlreadyExistException("Center already exist");
			}
		}
		else {
			throw new InvalidUserException("Admin only add the center");
		}
	}
	
	
	public DiagnosticCenterDto addTest(String userId, String centerId, String testId) throws UserNotFoundException,InvalidUserException,IdAlreadyExistException ,IdNotFoundException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new UserNotFoundException("User not found");
		}
		User user=userRepository.findById(userId).get();
//	    User user = userRepository.findById(userId).orElse(null);
 
	    if (user.getUserRole().equals("Admin")) {
	        DiagnosticCenter diagnosticCenter = diagnosticCenterRepository.findById(centerId).orElse(null);
 
	        if (diagnosticCenter != null) {
	            List<String> listOfTestId = centerTestMappingRepository.findTestIdByCenterId(centerId);
 
	            if (!listOfTestId.contains(testId)) {
	                CenterTestMapping centerTestMapping = new CenterTestMapping();
	                centerTestMapping.setTestid(testId);
	                centerTestMapping.setCenterId(centerId);
	                centerTestMappingRepository.save(centerTestMapping);
 
	                DiagnosticCenterDto diagnosticCenterDto2 = modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
 
	                List<TestDto> listOfTestDtos = new ArrayList<>();
	                List<CenterTestMapping> centerTestMappings = centerTestMappingRepository.findBycenterId(centerId);
 
	                for (CenterTestMapping ctm : centerTestMappings) {
	                    Test test = testRepository.findById(ctm.getTestid()).orElse(null);
 
	                    if (test != null) {
	                        TestDto testDto = modelMapper.map(test, TestDto.class);
	                        listOfTestDtos.add(testDto);
	                    } else {
	                    	throw new IdNotFoundException("Test Not Found");
	                        // Handle the case where test is not found (you can throw an exception if needed)
	                    }
	                }
 
	                diagnosticCenterDto2.setListOfTest(listOfTestDtos);
 
	                return diagnosticCenterDto2;
	            } else {
	                throw new IdAlreadyExistException("Test already exist in center ");
	            }
	        } else {
	            throw new IdNotFoundException("Center Id Not Found");
	        }
	    } else {
	        throw new InvalidUserException("Admin only add the Test");
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
