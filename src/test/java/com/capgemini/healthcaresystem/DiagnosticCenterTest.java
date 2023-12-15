//package com.capgemini.healthcaresystem;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
//import com.capgemini.healthcaresystem.entity.Tests;
//import com.capgemini.healthcaresystem.entity.User;
//import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
//import com.capgemini.healthcaresystem.exception.IdNotFoundException;
//import com.capgemini.healthcaresystem.exception.InvalidUserException;
//import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
//import com.capgemini.healthcaresystem.repository.UserRepository;
//import com.capgemini.healthcaresystem.service.DiagnosticCenterServiceImpl;
//
//@SpringBootTest
//public class DiagnosticCenterTest {
//
//	@Mock
//	private DiagnosticCenterRepository diagnosticCenterRepository;
//	
//	@InjectMocks
//	private DiagnosticCenterServiceImpl diagnosticCenterServiceImpl;
//	
//	@Mock
//	private UserRepository userRepository;
//	
//	@Mock
//	private ModelMapper modelMapper;
//	
//	
//	@Test
//	public void addCenterTest() throws IdNotFoundException, InvalidUserException, IdAlreadyExistException
//	{
//		String userId="1";
//		
//		User user = new User("1", "loghjj", "pass", BigInteger.valueOf(8786767575L), "admin","abc@gmail.com" ,45, "male");
//		
//		 List<Tests> tests = new ArrayList<Tests>();
//		   tests.add(new Tests( "BT01", "blood test"));
//		   tests.add(new Tests( "BT02", "sugar test"));
//		   
//		   
//			when(userRepository.findById(userId).get()).thenReturn(user);
//
//		
//		DiagnosticCenter diagnosticCenter = new DiagnosticCenter("dd", "applo",BigInteger.valueOf(667777888L), "btm", tests);
//	
//		
//		 when(diagnosticCenterRepository.save(diagnosticCenter)).thenReturn(diagnosticCenter);
//		 
//		 assertEquals(diagnosticCenter.getAddress(), diagnosticCenterServiceImpl.addCenter("1", diagnosticCenter).getAddress());
//		
//		
//		
//		
//		
//		
//	}
//}
