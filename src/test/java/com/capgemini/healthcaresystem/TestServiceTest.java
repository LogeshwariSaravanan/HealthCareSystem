package com.capgemini.healthcaresystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.repository.TestRepository;
import com.capgemini.healthcaresystem.repository.UserRepository;
import com.capgemini.healthcaresystem.service.TestServiceImpl;

@SpringBootTest
public class TestServiceTest {

	
	@Mock
	private TestRepository testRepository;
	
	@Mock 
	private UserRepository userRepository;
	
	@InjectMocks
	private TestServiceImpl testServiceImpl;
	
	
	@Test
	public void getAllTest()
	{
	   List<Tests> tests = new ArrayList<Tests>();
	   tests.add(new Tests( "BT01", "blood test"));
	   tests.add(new Tests( "BT02", "sugar test"));
	   
	   
	   when(testRepository.findAll()).thenReturn(tests);
	   
	   assertEquals(2, testServiceImpl.getTest().size());
	   assertEquals("blood test", tests.get(0).getTestName());
	   
    }

	//Add test by customer

	    @Test

	    public void testAddTestNonAdmin() {

	        String userId = "ravi123";

	        User customer = new User();

	        customer.setUserId(userId);

	        customer.setUserRole("Customer");
	 
	        Tests testAdd = new Tests();

	        testAdd.setTestName("NewTest");
	 
	        when(userRepository.findById(userId)).thenReturn(Optional.of(customer));
	 
	   

	        assertThrows(InvalidUserException.class, () -> testServiceImpl.addTest(userId, testAdd));

	    }
	
	
}
