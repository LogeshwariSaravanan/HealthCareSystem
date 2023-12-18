package com.capgemini.healthcaresystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.entity.CenterTestMapping;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.repository.AppointmentRepository;
import com.capgemini.healthcaresystem.repository.CenterTestMappingRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;
import com.capgemini.healthcaresystem.repository.UserRepository;
import com.capgemini.healthcaresystem.service.DiagnosticCenterServiceImpl;

@SpringBootTest
public class DiagnosticCenterTest {
	@Mock
	private DiagnosticCenterRepository diagnosticCenterRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private AppointmentRepository appointmentRepository;
	@Mock 
	private CenterTestMapping centerTestMapping;
	@Mock 
	private CenterTestMappingRepository centerTestMappingRepository;
	@Mock
	private TestRepository testRepository;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private DiagnosticCenterDto diagnosticCenterDto;
	@InjectMocks
	private DiagnosticCenterServiceImpl diagnosticCenterServiceImpl;
	
	@Test
	public void AddCenterTestCase() throws IdNotFoundException, InvalidUserException, IdAlreadyExistException {
	        // Mocking the user repository
	        User adminUser = new User();
	        adminUser.setUserId("adminUserId");
	        adminUser.setUserRole("Admin");
	        when(userRepository.findById("adminUserId")).thenReturn(Optional.of(adminUser));
 
	        // Mocking the diagnostic center repository
	        List<BigInteger> listOfCenterContactNo = Arrays.asList(BigInteger.valueOf(1234567890));
	        when(diagnosticCenterRepository.findAllCenterByContactNo()).thenReturn(listOfCenterContactNo);
	        when(diagnosticCenterRepository.save(any())).thenReturn(new DiagnosticCenter());
 
	        // Mocking the test repository
	        List<Tests> listOfTests = Arrays.asList(new Tests(), new Tests(), new Tests());
	        when(testRepository.findAll()).thenReturn(listOfTests);
 
	        // Mocking the center test mapping repository
	        when(centerTestMappingRepository.save(any())).thenReturn(new CenterTestMapping());
 
	        // Mocking the modelMapper
	        DiagnosticCenterDto diagnosticCenterDtoMock = new DiagnosticCenterDto();
	        when(modelMapper.map(any(), eq(DiagnosticCenterDto.class))).thenReturn(diagnosticCenterDtoMock);
 
	        // Call the addCenter method
	        DiagnosticCenterDto resultDto = diagnosticCenterServiceImpl.addCenter("adminUserId", new DiagnosticCenter());
 
	        // Assertions
	        assertNotNull(resultDto);
	        assertEquals(diagnosticCenterDtoMock, resultDto);
	        // Add more assertions as needed based on the expected behavior of the method
	    }
	
	
	@Test
    void testDeleteCenter() {
        // Arrange
        String userId = "someUserId";
        String diagnosticCenterId = "someDiagnosticCenterId";

        User adminUser = new User();
        adminUser.setUserId(userId);
        adminUser.setUserRole("Admin");

        DiagnosticCenter diagnosticCenter = new DiagnosticCenter();
        diagnosticCenter.setCenterId(diagnosticCenterId);

        // Mocking repository methods
        when(userRepository.findById(userId)).thenReturn(Optional.of(adminUser));
        when(diagnosticCenterRepository.existsById(diagnosticCenterId)).thenReturn(true);
        when(diagnosticCenterRepository.findById(diagnosticCenterId)).thenReturn(Optional.of(diagnosticCenter));
        when(appointmentRepository.findDiagnosticCenter()).thenReturn(new ArrayList<>());

        // Act
        try {
            boolean result = diagnosticCenterServiceImpl.deleteCenter(userId, diagnosticCenterId);

            // Assert
            assertTrue(result);
            verify(centerTestMappingRepository, times(1)).deleteCenterTestMappingByCenter(diagnosticCenter);
            verify(diagnosticCenterRepository, times(1)).deleteById(diagnosticCenterId);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }
	
	@Test
    public void DeleteTestcase() throws IdNotFoundException, InvalidUserException {
        // Mocking the user repository
        User adminUser = new User();
        adminUser.setUserId("adminUserId");
        adminUser.setUserRole("Admin");
        when(userRepository.findById("adminUserId")).thenReturn(Optional.of(adminUser));
 
        // Mocking the diagnostic center repository
        DiagnosticCenter diagnosticCenter = new DiagnosticCenter();
        when(diagnosticCenterRepository.existsById("diagnosticCenterId")).thenReturn(true);
        when(diagnosticCenterRepository.findById("diagnosticCenterId")).thenReturn(Optional.of(diagnosticCenter));
 
        // Mocking the test repository
        Tests test = new Tests();
        when(testRepository.existsById("testId")).thenReturn(true);
        when(testRepository.findById("testId")).thenReturn(Optional.of(test));
 
        // Mocking the center test mapping repository
        List<Tests> listOfTestInCenter = Arrays.asList(test);
        when(centerTestMappingRepository.findTestByCenter(diagnosticCenter)).thenReturn(listOfTestInCenter);
        doNothing().when(centerTestMappingRepository).deleteTestFromCenter(diagnosticCenter, test);
 
        // Call the deleteTest method
        String result = diagnosticCenterServiceImpl.deleteTest("adminUserId", "diagnosticCenterId", "testId");
 
        // Assertions
        assertNotNull(result);
        // Add more assertions as needed based on the expected behavior of the method
    }

	
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
//	}
	
	
	
}
