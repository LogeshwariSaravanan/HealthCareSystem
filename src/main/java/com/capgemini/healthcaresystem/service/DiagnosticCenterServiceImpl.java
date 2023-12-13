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
	public DiagnosticCenterDto addCenter(String userId, DiagnosticCenter diagnosticCenter) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
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
	
	
	@Override
	public boolean deleteCenter(String userId, String diagnosticCenterId) throws IdNotFoundException, InvalidUserException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		User user=userOptional.get();
		if(user.getUserRole().equals("Admin")) {
			if(diagnosticCenterRepository.existsById(diagnosticCenterId)) {
			diagnosticCenterRepository.deleteById(diagnosticCenterId);
			centerTestMappingRepository.deleteCenterTestMappingById(diagnosticCenterId);
			return true;
		}
		else {
		  throw new IdNotFoundException("Center Id Not Found");
		}
		}else {
			throw new InvalidUserException("Admin only add the Test");
		}
	}

	
	
	
	public DiagnosticCenterDto addTest(String userId,String centerId, String testId)throws IdAlreadyExistException, IdNotFoundException, InvalidUserException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		User user=userRepository.findById(userId).get();
		if(user.getUserRole().equals("Admin")) {
			Optional<DiagnosticCenter> diagnosticCenterOptional=diagnosticCenterRepository.findById(centerId);
			if(diagnosticCenterOptional.isEmpty()) {
				throw new IdNotFoundException("Center not found");
			}
			else {
				List<String> listOfTestId=centerTestMappingRepository.findTestIdByCenterId(centerId);
				List<String> listOfTest=testRepository.listOfTestId();
				if(listOfTest.contains(testId)) {
					if(!listOfTestId.contains(testId)) {
					    CenterTestMapping centerTestMapping=new CenterTestMapping();
						centerTestMapping.setTestid(testId);
						centerTestMapping.setCenterId(centerId);
						centerTestMappingRepository.save(centerTestMapping);
						DiagnosticCenter diagnosticCenter=diagnosticCenterOptional.get();
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
		}
		else {
			throw new InvalidUserException("Admin only add the center");
		}
	}

	@Override
	public boolean deleteTest(String userId, String diagnosticCenterId, String testId) throws IdNotFoundException,InvalidUserException{
		 
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		User user=userOptional.get();
	    if (user.getUserRole().equals("Admin")) {
	        if (diagnosticCenterRepository.existsById(diagnosticCenterId)) {
	            if (testRepository.existsById(testId)) {
	                centerTestMappingRepository.deletebyCenterIdAndTestId(diagnosticCenterId, testId);
	                return true;
	            } else {
	                throw new IdNotFoundException("test id does not exist");
	            }
	        } else {
	            throw new IdNotFoundException("center id not found exception");
	        }
	    } else {
	    	throw new InvalidUserException("Admin only add the Test");
	    }
	}



	



	
}
