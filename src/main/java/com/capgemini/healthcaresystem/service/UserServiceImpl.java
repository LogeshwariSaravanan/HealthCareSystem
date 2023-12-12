package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.DiagnosticCenterDto;
import com.capgemini.healthcaresystem.dto.TestDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.DiagnosticCenter;
import com.capgemini.healthcaresystem.entity.Test;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;
import com.capgemini.healthcaresystem.exception.UserNotFoundException;
import com.capgemini.healthcaresystem.repository.AppointmentRepository;
import com.capgemini.healthcaresystem.repository.CenterTestMappingRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;
import com.capgemini.healthcaresystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	CenterTestMappingRepository centerTestMappingRepository;
	
	@Autowired
	ModelMapper modelMapper;

	

	@Override
	public UserDto addUser(User user) throws InvalidUserNameException,InvalidPasswordException ,InvalidContactNumberException,InvalidEmailIdException{
        String userNameRegex = "^[A-Z][a-zA-Z0-9]*$";
        if(user.getUserName().matches(userNameRegex)) {
        	String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,14}$";
        	if(user.getUserPassword().matches(passwordRegex)) {
        		if(user.getContactNo().toString().length() == 10) {
        			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        			if(user.getUserEmail().matches(emailRegex)) {
        				userRepository.save(user);
                		UserDto userDto2=modelMapper.map(user,UserDto.class);
//                		userDto2.setUserRole(user.getUserRole());
                		return userDto2;
        			}
        			else {
        				throw new InvalidEmailIdException("Not a valid Email");
        			}
        		}
        		else {
        			throw new InvalidContactNumberException(" Not a valid ContactNumber");
        		}
        		}
        	else {
        		throw new InvalidPasswordException("Not valid password");
    		}
        }
		else {
		  throw new InvalidUserNameException("Not a valid name");
		}
		
	}
	
	
	
	public List<UserDto> getUser() {
		// TODO Auto-generated method stub
		List<User> listOfUser=userRepository.findAll();
		
		List<UserDto> listOfUserDto=new ArrayList<>();
		for(User user:listOfUser) {
			UserDto userDto=modelMapper.map(user, UserDto.class);
			listOfUserDto.add(userDto);
		}
		
		return listOfUserDto;
		
	}
	
		

	public AppointmentDto makeAppointment(String userId, String diagnosticCenterid, String testId,LocalDateTime dateAndTime) throws UserNotFoundException, IdNotFoundException {
		
		Appointment appointment=new Appointment();
	Optional<User> userOptional=userRepository.findById(userId);
	if(userOptional.isEmpty())
	{
		throw new UserNotFoundException("User not found");
	}
	      User user = userOptional.get();
		  appointment.setUser(user);
		  
		  Optional<DiagnosticCenter> diagnosticCenterOptional=diagnosticCenterRepository.findById(diagnosticCenterid);
	      if(diagnosticCenterOptional.isEmpty()) {
	    	  throw new IdNotFoundException("Center not found");
	      }
		  DiagnosticCenter diagnosticCenter=diagnosticCenterOptional.get();
		  appointment.setDiagnosticCenter(diagnosticCenter);
		  
		  List<String> listOfTest=centerTestMappingRepository.findTestIdByCenterId(diagnosticCenterid);
		  if(!listOfTest.contains(testId)) {
			  throw new IdNotFoundException("Test not found in this Center");
		  }
		  Test test=testRepository.findById(testId).get();
		  appointment.setTest(test);
		  appointment.setDateAndTime(dateAndTime);
		  appointmentRepository.save(appointment);
					
		  AppointmentDto appointmentDto2= modelMapper.map(appointment, AppointmentDto.class);
		  
		  UserDto userDto=modelMapper.map(user,UserDto.class);
		  appointmentDto2.setUserDto(userDto);
		  
		  DiagnosticCenterDto diagnosticCenterDto=modelMapper.map(diagnosticCenter,DiagnosticCenterDto.class);
		  appointmentDto2.setDiagnosticCenterDto(diagnosticCenterDto);
		  
		  TestDto testDto=modelMapper.map(test, TestDto.class);
		  appointmentDto2.setTestDto(testDto);
		  
		  return appointmentDto2;
		  }
			
			
		
		
	

	
	public boolean approveAppointment(String userId, String diagnosticCenterId) throws InvalidUserException, IdNotFoundException{
		Optional<User> userOptional=userRepository.findById(userId);
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		User user=userOptional.get();
		if(user.getUserRole().equals("Admin")) {
			Optional<DiagnosticCenter> diagnosticCenterOptional=diagnosticCenterRepository.findById(diagnosticCenterId);
			if(diagnosticCenterOptional.isEmpty()) {
				throw new IdNotFoundException("center is not present");
			}
			DiagnosticCenter diagnosticCenter=diagnosticCenterOptional.get();
			List<Appointment> listOfAppointment=appointmentRepository.findByDiagnosticCenter(diagnosticCenter);
			for(Appointment appointment:listOfAppointment) {
				appointmentRepository.approveappointment(appointment.getAppointmentId());
			}
			return true;
		}
		else {
			
			throw new InvalidUserException("Admin only approve the appointment");
		}
		
	}

	

	

}
