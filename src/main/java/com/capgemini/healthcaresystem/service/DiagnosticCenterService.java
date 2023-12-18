package com.capgemini.healthcaresystem.service;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;

public interface DiagnosticCenterService {

	public DiagnosticCenterDto addCenter(String userId,DiagnosticCenter diagnosticCenter) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException;

	public DiagnosticCenterDto addTest(String userId, String centerId, String testId) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException;

	public boolean deleteCenter(String userId, String diagnosticCenterId) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException;

	public String deleteTest(String userId, String diagnosticCenterId, String testId) throws IdNotFoundException, InvalidUserException;

	
	

}
