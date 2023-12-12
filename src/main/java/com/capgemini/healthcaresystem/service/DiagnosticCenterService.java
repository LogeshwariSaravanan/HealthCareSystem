package com.capgemini.healthcaresystem.service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.UserNotFoundException;

public interface DiagnosticCenterService {

	public DiagnosticCenterDto addCenter(String userId,DiagnosticCenter diagnosticCenter) throws UserNotFoundException, InvalidUserException;

	public DiagnosticCenterDto addTest(String userId, String centerId, String testId) throws UserNotFoundException, InvalidUserException, IdAlreadyExistException, IdNotFoundException;

	public boolean deleteCenter(String userId, String diagnosticCenterId);

	public boolean deleteTest(String userId, String diagnosticCenterId, String testId);
	
	
	

}
