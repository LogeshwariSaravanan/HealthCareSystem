package com.capgemini.healthcaresystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.service.TestService;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	TestService testService;
	
	
@PostMapping("/add")
public Test addTest(@RequestBody Test test)
   {
//	System.out.println(test.getDiagnosticCenter().getCenterId());
	testService.addTest(test);
	return test;
}

@GetMapping("/get")
public List<Test> getTest(){
	return testService.getTest();
}




	
	

}
