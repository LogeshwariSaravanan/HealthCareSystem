package com.capgemini.healthcaresystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.service.TestService;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	TestService testService;
	
	
@PostMapping("/add/{userid}")
public TestDto addTest(@PathVariable(value="userid") String userId,@RequestBody Tests test) throws IdNotFoundException, InvalidUserException, IdAlreadyExistException
   {
	return testService.addTest(userId,test);
}

@GetMapping("/get")
public List<Tests> getTest(){
	return testService.getTest();
}

@GetMapping("/get/{id}")
public Tests getTestById(@PathVariable String id) throws IdNotFoundException {
	return testService.getTestById(id);
}

@DeleteMapping("deletetest/{id}")
ResponseEntity<String> deleteTest(@PathVariable (value="id") String testId) throws IdNotFoundException{
	return new ResponseEntity<String>(testService.deleteTest(testId),HttpStatus.OK);
}



	
	

}
