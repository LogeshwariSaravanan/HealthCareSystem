package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
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
import com.capgemini.healthcaresystem.entity.Tests;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;
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
	public UserDto addUser(User user) throws InvalidUserNameException,InvalidPasswordException ,InvalidContactNumberException,InvalidEmailIdException,IdAlreadyExistException{
        String userNameRegex = "^[A-Z][a-zA-Z0-9]*$";
        if(user.getUserName().matches(userNameRegex)) {
        	String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,14}$";
        	if(user.getUserPassword().matches(passwordRegex)) {
        		if(user.getContactNo().toString().length() == 10) {
        			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        			if(user.getUserEmail().matches(emailRegex)) {
        				List<BigInteger> ListOfUserContactNo=userRepository.FindAllUserByContactNo();
        				if(!ListOfUserContactNo.contains(user.getContactNo())) {
        					userRepository.save(user);
                    		UserDto userDto2=modelMapper.map(user,UserDto.class);
                    		return userDto2;
        				}
        				else {
        					throw new IdAlreadyExistException("User Already Exist");
        				}
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


	@Override
	public String login(User user) throws IdNotFoundException, InvalidPasswordException {
		Optional<User> useroptional=userRepository.findById(user.getUserId());
		if(useroptional.isEmpty()) {
			throw new IdNotFoundException("User not found please register");
		}
		User user1=useroptional.get();
		if(user1.getUserPassword().equals(user.getUserPassword())) {
			if(user1.getUserRole().equals("Admin")) {
				return "welcome Admin";
			}
			else {
				return "welcome "+user1.getUserName(); 
			}
		}
		else {
			throw new InvalidPasswordException("Invalid password please try again");
		}
		
	}
	
	
	@Override
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
	
	
	@Override
	public AppointmentDto makeAppointment(Appointment appointment) throws IdNotFoundException, IdAlreadyExistException {
		Optional<User> userOptional=userRepository.findById(appointment.getUser().getUserId());
		if(userOptional.isEmpty())
		{
			throw new IdNotFoundException("User not found");
		}
		      User user = userOptional.get();
			  appointment.setUser(user);
			  
			  List<User> listOfUser=appointmentRepository.findUser();
			  if(!listOfUser.contains(appointment.getUser())) {
				  Optional<DiagnosticCenter> diagnosticCenterOptional=diagnosticCenterRepository.findById(appointment.getDiagnosticCenter().getCenterId());
			      if(diagnosticCenterOptional.isEmpty()) {
			    	  throw new IdNotFoundException("Center not found");
			      }
				  DiagnosticCenter diagnosticCenter=diagnosticCenterOptional.get();
				  appointment.setDiagnosticCenter(diagnosticCenter);
				  
				  List<Tests> listOfTest=centerTestMappingRepository.findTestByCenter(diagnosticCenter);
				  Tests test=testRepository.findById(appointment.getTest().getTestid()).get();
				  appointment.setTest(test);
				  if(listOfTest.contains(appointment.getTest())) {
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
				  throw new IdNotFoundException("Test not found in this Center");
				  
			  }
			  else {
				  throw new IdAlreadyExistException("One user make only one appointment");
			  }		
	}


	@Override
	public String approveAppointment(String userId, String diagnosticCenterId) throws InvalidUserException, IdNotFoundException{
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
			if(listOfAppointment.isEmpty()) {
				return "no appointments to approve in center "+diagnosticCenterId;
			}
			else {
				for(Appointment appointment:listOfAppointment) {
					appointmentRepository.approveappointment(appointment.getAppointmentId());
				}
				return "All appointments are approved in the center "+diagnosticCenterId;
			}
		}
		else {
			
			throw new InvalidUserException("Admin only approve the appointment");
		}
		
	}


	@Override
	public String checkStatus(int appointmentId) throws IdNotFoundException {
		if(appointmentRepository.existsById(appointmentId)) {
			Appointment appointment=appointmentRepository.findById(appointmentId).get();
			if(appointment.isApproved()==true) {
				return "Appointment id : "+appointmentId+"\nAppointment Status : Approved";
			}
			else {
				return "Appointment id : "+appointmentId+"\nAppointment Status : Pending";
			}
		}
		throw new IdNotFoundException("Appointment not Found");
	}


	@Override
	public String cancelAppointment(int appointmentId) throws IdNotFoundException {
		if(appointmentRepository.existsById(appointmentId)) {
			appointmentRepository.deleteById(appointmentId);
			return "Appointment id : "+appointmentId+" cancelled successfully";
				}
		throw new IdNotFoundException("Appointment not Found");
	}


	

	


	

	

	

}
