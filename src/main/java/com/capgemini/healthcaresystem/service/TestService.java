package com.capgemini.healthcaresystem.service;

import java.util.List;

import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;

public interface TestService {
	public TestDto addTest(String userId,Test test) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException;

	public List<Test> getTest();

	public Test getTestById(String id) throws IdNotFoundException;

	public String deleteTest(String testId) throws IdNotFoundException;


}
