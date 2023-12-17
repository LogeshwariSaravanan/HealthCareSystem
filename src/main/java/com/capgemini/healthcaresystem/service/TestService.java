package com.capgemini.healthcaresystem.service;

import java.util.List;

import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;

public interface TestService {
	public TestDto addTest(String userId,Tests test) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException;

	public List<Tests> getTest();

	public Tests getTestById(String id) throws IdNotFoundException;

	public String deleteTest(String userId, String testId) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException;


}
