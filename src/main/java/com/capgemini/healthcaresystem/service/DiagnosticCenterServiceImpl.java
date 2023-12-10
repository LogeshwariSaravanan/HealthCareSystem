package com.capgemini.healthcaresystem.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.entity.User;
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
		testDtos.add(new TestDto("bt","blood Test"));
		testDtos.add(new TestDto("st","sugar Test"));
		testDtos.add(new TestDto("bp","Blood Pressure"));
		
	}
	
	
	@Override
	public DiagnosticCenterDto addCenter(String userId, DiagnosticCenter diagnosticCenter) {
		User user=userRepository.findById(userId).get();
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
			return null;
		}
	}
	
	
	public DiagnosticCenterDto addTest(String userId,String centerId, String testId) {
		User user=userRepository.findById(userId).get();
		if(user.getUserRole().equals("Admin")) {
			
			List<String> ListOfTestId=centerTestMappingRepository.findTestIdByCenterId(centerId);
			
			if(!ListOfTestId.contains(testId)) {
			
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
				return null;
			}
			
		 	}
		else {
			return null;
		}
				
	}


	


	

	}
