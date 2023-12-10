package com.capgemini.healthcaresystem.service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;

public interface DiagnosticCenterService {

	public DiagnosticCenterDto addCenter(String userId,DiagnosticCenter diagnosticCenter);

	public DiagnosticCenterDto addTest(String userId, String centerId, String testId);

	public boolean deleteCenter(String userId, String diagnosticCenterId);

	public boolean deleteTest(String userId, String diagnosticCenterId, String testId);
	
	
	

}
