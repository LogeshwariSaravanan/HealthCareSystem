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
import com.capgemini.healthcaresystem.repository.CenterTestMappingRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;


@Service
public class DiagnosticCenterService {
	@Autowired 
	DiagnosticCenterRepository diagnosticCenterrepo;
	@Autowired
	TestRepository testRepository;
	@Autowired
	CenterTestMappingRepository centerTestMappingRepository;
	
	@Autowired
	ModelMapper modelMapper;
	private static List<TestDto> testDtos=new ArrayList();
	static {
		testDtos.add(new TestDto("bt","blood Test"));
		testDtos.add(new TestDto("ut","urine Test"));
		testDtos.add(new TestDto("st","sugar Test"));
		
	}

	public DiagnosticCenterDto addCenter(DiagnosticCenterDto diagnosticCenterDto) {
		
//		diagnosticCenterDto.setListOfTest(testDtos);
		DiagnosticCenter diagnosticCenter=modelMapper.map(diagnosticCenterDto, DiagnosticCenter.class);
		diagnosticCenterrepo.save(diagnosticCenter);
			
		for(int i=0;i<3;i++) {
			CenterTestMapping centerTestMapping=new CenterTestMapping();
		    centerTestMapping.setTestid(diagnosticCenterDto.getListOfTest().get(i).getTestid());
		    centerTestMapping.setCenterId(diagnosticCenterDto.getCenterId());
		    centerTestMappingRepository.save(centerTestMapping);
		}
		
		DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
		diagnosticCenterDto2.setListOfTest(testDtos);
		return diagnosticCenterDto2;
		
	}
	
	
	public DiagnosticCenterDto addTest(String centerId, String testId) {
		 CenterTestMapping centerTestMapping=new CenterTestMapping();
			centerTestMapping.setTestid(testId);
			centerTestMapping.setCenterId(centerId);
			centerTestMappingRepository.save(centerTestMapping);
			
//			Test test=testRepository.findById(testId).get();
//			TestDto testDto=modelMapper.map(test, TestDto.class);
			
			DiagnosticCenter diagnosticCenter=diagnosticCenterrepo.findById(centerId).get();
			DiagnosticCenterDto diagnosticCenterDto2=modelMapper.map(diagnosticCenter, DiagnosticCenterDto.class);
			
		 	List<TestDto> listOfTestDtos=new ArrayList<>();
		    List<CenterTestMapping> centerTestMappings=	centerTestMappingRepository.findBycenterId(centerId);
		    for(CenterTestMapping ctm:centerTestMappings) {
			    Test test=testRepository.findById(ctm.getTestid()).get();
			    TestDto testDto=modelMapper.map(test,TestDto.class);
			    listOfTestDtos.add(testDto);
			    }
		    diagnosticCenterDto2.setListOfTest(listOfTestDtos);
		  
		 	
//		 	List<String> testIds=centerTestMappingRepository.findBycenterId(centerId);
//		 	System.out.println(testIds);
		 	return diagnosticCenterDto2;
				
	}
}
