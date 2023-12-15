//package com.capgemini.healthcaresystem;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.capgemini.healthcaresystem.entity.Tests;
//import com.capgemini.healthcaresystem.repository.TestRepository;
//import com.capgemini.healthcaresystem.service.TestService;
//import com.capgemini.healthcaresystem.service.TestServiceImpl;
//
//@SpringBootTest
//public class TestServiceTest {
//
//	
//	@Mock
//	private TestRepository testRepository;
//	
//	@InjectMocks
//	private TestServiceImpl testServiceImpl;
//	
//	
//	@Test
//	public void getAllTest()
//	{
//	   List<Tests> tests = new ArrayList<Tests>();
//	   tests.add(new Tests( "BT01", "blood test"));
//	   tests.add(new Tests( "BT02", "sugar test"));
//	   
//	   
//	   when(testRepository.findAll()).thenReturn(tests);
//	   
//	   assertEquals(2, testServiceImpl.getTest().size());
//	   assertEquals("bloodtest", tests.get(0).getTestName());
//	   
//    }
//}
