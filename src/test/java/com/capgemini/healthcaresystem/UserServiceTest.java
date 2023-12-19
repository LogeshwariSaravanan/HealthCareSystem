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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidDateException;
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
    
  
    @Test
    void makeAppointmentTest() throws IdNotFoundException, IdAlreadyExistException, InvalidDateException {
        // Mock data
        User user = new User("Log123","Logeshwari","log@123",BigInteger.valueOf(8786767575L),"Customer","logi@gmail.com",20,"female");
        DiagnosticCenter diagnosticCenter = new DiagnosticCenter("che123", "apollo",BigInteger.valueOf(8786767574L) ,"Chennai");
        Tests test = new Tests("B123","Blood Test");
        Appointment appointment = new Appointment(BigInteger.valueOf(1L),LocalDateTime.now().plusDays(1),false,test,user,diagnosticCenter);
        appointment.setUser(user);
        appointment.setDiagnosticCenter(diagnosticCenter);
        appointment.setTest(test);

        // Mocking repository calls
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(diagnosticCenterRepository.findById(diagnosticCenter.getCenterId())).thenReturn(Optional.of(diagnosticCenter));
        when(testRepository.existsById(test.getTestid())).thenReturn(true);
        when(testRepository.findById(test.getTestid())).thenReturn(Optional.of(test));
        
        List<Tests> listOfTest = Arrays.asList(test);
        when(centerTestMappingRepository.findTestByCenter(diagnosticCenter)).thenReturn(listOfTest);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        // Perform the test
        Appointment result=userServiceImpl.makeAppointment(appointment);
        assertEquals(appointment, result);
        assertEquals("B123",appointment.getTest().getTestid());
    }
    
    
    @Test
    void approveAppointmenttest() throws InvalidUserException, IdNotFoundException {
        // Mock data
        String userId = "admin";
        String diagnosticCenterId = "che123";
        User adminUser = new User(userId,"Admin","admin@123",BigInteger.valueOf(8786767579L),"Admin","admin@gmail.com",0,"Male");
        DiagnosticCenter diagnosticCenter = new DiagnosticCenter(diagnosticCenterId, "apollo",BigInteger.valueOf(8786767574L) ,"Chennai");
        List<Appointment> listOfAppointments = new ArrayList<>(); // Add appointments as needed

        // Mocking repository calls
        when(userRepository.findById(userId)).thenReturn(Optional.of(adminUser));
        when(diagnosticCenterRepository.findById(diagnosticCenterId)).thenReturn(Optional.of(diagnosticCenter));
        when(appointmentRepository.findByDiagnosticCenter(diagnosticCenter)).thenReturn(listOfAppointments);

        // Perform the test
        String result = userServiceImpl.approveAppointment(userId, diagnosticCenterId);

        // Assert the result
        assertEquals("No appointments to approve in center " + diagnosticCenterId, result);
    }
    
    @Test
    void cancelAppointment_shouldCancelAppointment() throws IdNotFoundException {
        // Mock appointmentId
        int appointmentId = 1;

        // Mock repository behavior
        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);

        // Test the service method
        String result = userServiceImpl.cancelAppointment(appointmentId);

        // Verify the result using assertEquals
        assertEquals("Appointment id : " + appointmentId + " cancelled successfully", result);
    }
    
   
}
    
   
   
    




    

	
	
	

