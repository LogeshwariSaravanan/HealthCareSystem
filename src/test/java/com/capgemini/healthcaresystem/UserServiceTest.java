package com.capgemini.healthcaresystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
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
import com.capgemini.healthcaresystem.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private AppointmentRepository appointmentRepository;

    @Mock
    private DiagnosticCenterRepository diagnosticCenterRepository;
 
    @Mock
    private TestRepository testRepository;
 
    @Mock
    private CenterTestMappingRepository centerTestMappingRepository;
    @InjectMocks
	private UserServiceImpl userServiceImpl;
    
    @Test
    public void getUserTest() {
       // Mocking data
       List<User> mockListOfUsers = new ArrayList<>();
       // Populate mockListOfUsers with some User instances

       // Mocking repository method
       when(userRepository.findAll()).thenReturn(mockListOfUsers);

       // Calling the method
       List<UserDto> result = userServiceImpl.getUser();

       // Verifying the results
       assertEquals(mockListOfUsers.size(), result.size());

       // Add more assertions based on your specific logic
   }
    
    
    @Test
    public void LoginTest() {
        // Mocking data
        User user = new User();
        user.setUserId("someUserId");
        user.setUserPassword("validPassword");

        // Mocking repository methods
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        // Calling the method
        try {
            String result = userServiceImpl.login(user);

            // Verifying the results
            assertNotNull(result);
            // Add more assertions based on your specific logic

            // Verifying that repository methods were called
            verify(userRepository).findById(user.getUserId());

        } catch (Exception e) {
            // If an exception is thrown, the test should fail
            fail("Exception not expected: " + e.getMessage());
        }
    }
    
    @Test
    public void loginUserNotFoundTest() {
        // Mocking data
        User user = new User();
        user.setUserId("nonExistentUserId");
        // Mocking repository methods
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
        // Calling the method and expecting an IdNotFoundException
        assertThrows(IdNotFoundException.class, () -> {
        	userServiceImpl.login(user);
        });
        // Verifying that repository methods were called
        verify(userRepository).findById(user.getUserId());
    }
    
    @Test
	   public void testCheckStatusWhenAppointmentExistsAndIsApproved() throws IdNotFoundException {
	        // Arrange
	        int appointmentId = 1;
	        Appointment appointment = new Appointment();
	        appointment.setApproved(true);
	        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
	        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
	        // Act
	        String result = userServiceImpl.checkStatus(appointmentId);
	        // Assert
	        assertEquals("Appointment id : 1\nAppointment Status : Approved", result);
	        verify(appointmentRepository, times(1)).existsById(appointmentId);
	        verify(appointmentRepository, times(1)).findById(appointmentId);
	    }
    @Test
    public void testCheckStatusWhenAppointmentExistsAndNotApproved() throws IdNotFoundException {
    	// Arrange
    	int appointmentId = 1;
    	Appointment appointment = new Appointment();
    	appointment.setApproved(false);
    	when(appointmentRepository.existsById(appointmentId)).thenReturn(true);
    	when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
    	// Act
    	String result = userServiceImpl.checkStatus(appointmentId);
    	// Assert
    	assertEquals("Appointment id : 1\nAppointment Status : Pending", result);
//	        verify(appointmentRepository, times(1)).existsById(appointmentId);
//	        verify(appointmentRepository, times(1)).findById(appointmentId);
    }
    
    

}



    

	
	
	

