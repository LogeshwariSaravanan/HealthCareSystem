package com.capgemini.healthcaresystem.service;

import java.util.List;

import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;

public interface TestService {
	public TestDto addTest(TestDto testDto);

	public List<Test> getTest();

	public Test getTestById(String id) throws IdNotFoundException;

}
