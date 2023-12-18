package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.repository.AppointmentRepository;
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
	AppointmentRepository appointmentRepository;
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
			List<BigInteger> listOfCenterContactNo=diagnosticCenterRepository.findAllCenterByContactNo();
			if(!listOfCenterContactNo.contains(diagnosticCenter.getContactNo())) {
				diagnosticCenterRepository.save(diagnosticCenter);
				List<Tests> listOfTest=testRepository.findAll();
				List<Tests> defaultTestList=new ArrayList<>();
				for(int i=0;i<3;i++) {
					Tests tests=listOfTest.get(i);
					defaultTestList.add(tests)	;
				}
				for(Tests tests:defaultTestList) {
					CenterTestMapping centerTestMapping=new CenterTestMapping();
				    centerTestMapping.setTest(tests);
				    centerTestMapping.setDiagnosticCenter(diagnosticCenter);
				    centerTestMappingRepository.save(centerTestMapping);
				}
				
				DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
				List<TestDto> defaultTestDtoList=new ArrayList<>();
				for(int i=0;i<3;i++) {
					TestDto testDto=modelMapper.map(listOfTest.get(i),TestDto.class);
					defaultTestDtoList.add(testDto);
				}
				diagnosticCenterDto2.setListOfTest(defaultTestDtoList);

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
	public DiagnosticCenterDto addTest(String userId, String centerId, String testId)throws IdNotFoundException, InvalidUserException, IdAlreadyExistException, IdNotFoundException {
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
				DiagnosticCenter diagnosticCenter=diagnosticCenterOptional.get();
				List<Tests> listOfTestInCenter=centerTestMappingRepository.findTestByCenter(diagnosticCenter);
				List<String> listOfTest=testRepository.findAllTestId();
				if(listOfTest.contains(testId)) {
					Tests test=testRepository.findById(testId).get();
					if(!listOfTestInCenter.contains(test)) {
					    CenterTestMapping centerTestMapping=new CenterTestMapping();
						centerTestMapping.setTest(test);
						centerTestMapping.setDiagnosticCenter(diagnosticCenter);
						centerTestMappingRepository.save(centerTestMapping);
						DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
					 	List<TestDto> listOfTestDtos=new ArrayList<>();
					    List<CenterTestMapping> centerTestMappings=	centerTestMappingRepository.findBydiagnosticCenter(diagnosticCenter);
					    for(CenterTestMapping ctm:centerTestMappings) {
						    Tests tests=testRepository.findById(ctm.getTest().getTestid()).get();
						    TestDto testDto=modelMapper.map(tests,TestDto.class);
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
	public boolean deleteCenter(String userId, String diagnosticCenterId)throws IdNotFoundException, InvalidUserException, IdAlreadyExistException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		User user=userOptional.get();
		if(user.getUserRole().equals("Admin")) {
			if(diagnosticCenterRepository.existsById(diagnosticCenterId)) {
				DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(diagnosticCenterId).get();
				List<DiagnosticCenter> listOfDiagnosticCenter=appointmentRepository.findDiagnosticCenter();
				if(!listOfDiagnosticCenter.contains(diagnosticCenter)) {
					centerTestMappingRepository.deleteCenterTestMappingByCenter(diagnosticCenter);
				    diagnosticCenterRepository.deleteById(diagnosticCenterId);
				    return true;
				}else {
					throw new IdAlreadyExistException("Appointment found in this center you can't remove the center");
				}
			
			
		}
		else {
		  throw new IdNotFoundException("Center Id Not Found");
		}
		}else {
			throw new InvalidUserException("Admin only delete the center");
		}
		
	}


	@Override
	public String deleteTest(String userId, String diagnosticCenterId, String testId) throws IdNotFoundException, InvalidUserException {
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		User user=userOptional.get();
	    if (user.getUserRole().equals("Admin")) {
	        if (diagnosticCenterRepository.existsById(diagnosticCenterId)) {
	        	DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(diagnosticCenterId).get();
	        	List<Tests> listOfTestInCenter=centerTestMappingRepository.findTestByCenter(diagnosticCenter);
	            if (testRepository.existsById(testId)) {
	            	Tests test=testRepository.findById(testId).get();
	            	if(listOfTestInCenter.contains(test)) {
		                centerTestMappingRepository.deleteTestFromCenter(diagnosticCenter, test);
		                return test.getTestName()+" is deleted from the center "+diagnosticCenter.getCenterName();
	            	}
	            	else {
	            		throw new IdNotFoundException("Test not present in this Center");
	            	}
	            } else {
	                throw new IdNotFoundException("test id does not exist");
	            }
	        } else {
	            throw new IdNotFoundException("center not found exception");
	        }
	    } else {
	    	throw new InvalidUserException("Admin only add the Test");
	    }
		
	}




	



	
}
