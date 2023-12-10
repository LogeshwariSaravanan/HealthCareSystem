package com.capgemini.healthcaresystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.service.TestService;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	TestService testService;
	
	
@PostMapping("/add")
public TestDto addTest(@RequestBody TestDto testDto)
   {
	return testService.addTest(testDto);
}

@GetMapping("/get")
public List<Test> getTest(){
	return testService.getTest();
}

@GetMapping("/get/{id}")
public Test getTestById(@PathVariable String id) throws IdNotFoundException {
	return testService.getTestById(id);
}



	
	

}
