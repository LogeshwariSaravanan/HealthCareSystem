package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.capgemini.healthcaresystem.repository.AppointmentRepository;
import com.capgemini.healthcaresystem.repository.DiagnosticCenterRepository;
import com.capgemini.healthcaresystem.repository.TestRepository;
import com.capgemini.healthcaresystem.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public User addUser(User user) {
		
		return userRepository.save(user);
	}

	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public AppointmentDto makeAppointment(String userId, String diagnosticCenterid, String testId,LocalDateTime dateAndTime) {
		Appointment appointment=new Appointment();
		User user=userRepository.findById(userId).get();
		appointment.setUser(user);
		DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(diagnosticCenterid).get();
		appointment.setDiagnosticCenter(diagnosticCenter);
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

	
	

	public boolean approveAppointment(String userId, BigInteger appointmentId) {
		
		User user=userRepository.findById(userId).get();
		if(user.getUserRole().equals("Admin")) {
			appointmentRepository.approveappointment(appointmentId);
			return true;
		}
		else {
			
		    return false;
		}
	}

	

}
