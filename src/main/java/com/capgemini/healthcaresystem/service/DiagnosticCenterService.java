package com.capgemini.healthcaresystem.service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;

public interface DiagnosticCenterService {

	public DiagnosticCenterDto addCenter(String userId,DiagnosticCenter diagnosticCenter);

	public DiagnosticCenterDto addTest(String userId, String centerId, String testId);
	
	
	

}
