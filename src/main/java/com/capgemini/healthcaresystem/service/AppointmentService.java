package com.capgemini.healthcaresystem.service;

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
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	TestRepository testRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;

//	public Appointment addAppointment(Appointment appointment) {
		
		//Test test = testRepository.findById(appointment.getTest().getTestid()).get();
//		appointment.setTest(test);
//		
//		User user= userRepository.findById(appointment.getUser().getUserId()).get();
//		appointment.setUser(user);
//		
//		DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(appointment.getDiagnosticCenter().getCenterId()).get();
//		appointment.setDiagnosticCenter(diagnosticCenter);
//		
		
		
		
//		return  appointmentRepository.save(appointment);
//	}

	
		/*
		 * public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
		 * AppointmentDto appointmentDto2 = new AppointmentDto();
		 * 
		 * Appointment appointment = new Appointment();
		 * 
		 * appointment.setDateAndTime(appointmentDto.getDateAndTime());
		 * appointment.setApproved(appointmentDto.isApproved());
		 * 
		 * 
		 * Test test =
		 * testRepository.findById(appointmentDto.getTestDto().getTestid()).get(); User
		 * user =
		 * userRepository.findById(appointmentDto.getUserDto().getUserId()).get();
		 * DiagnosticCenter center =
		 * diagnosticCenterRepository.findById(appointmentDto.getDiagnosticCenterDto().
		 * getCenterId()).get();
		 * 
		 * appointment.setTest(test); appointment.setUser(user);
		 * appointment.setDiagnosticCenter(center);
		 * 
		 * Appointment appointment2 = appointmentRepository.save(appointment); //
		 * appointmentDto2.set
		 * 
		 * appointmentDto2.setAppointmentId(appointment2.getAppointmentId());
		 * appointmentDto2.setApproved(appointment2.isApproved());
		 * appointmentDto2.setAppointmentId(appointment2.getAppointmentId());
		 * 
		 * Test test2 =
		 * testRepository.findById(appointment2.getTest().getTestid()).get();
		 * 
		 * TestDto testDto2 = new TestDto(); testDto2.setTestid(test2.getTestid());
		 * testDto2.setTestName(test2.getTestName());
		 * 
		 * 
		 * appointmentDto2.setTestDto(testDto2);
		 * 
		 * User user2 =
		 * userRepository.findById(appointment2.getUser().getUserId()).get();
		 * 
		 * UserDto userDto2=new UserDto(); userDto2.setUserId(user2.getUserId());
		 * userDto2.setUserName(user2.getUserName());
		 * userDto2.setUserPassword(user2.getUserPassword());
		 * userDto2.setContactNo(user2.getContactNo());
		 * userDto2.setUserRole(user2.getUserRole());
		 * userDto2.setUserEmail(user2.getUserEmail()); userDto2.setAge(user2.getAge());
		 * userDto2.setGender(user2.getGender());
		 * 
		 * appointmentDto2.setUserDto(userDto2);
		 * 
		 * DiagnosticCenter
		 * diagnosticCenter2=diagnosticCenterRepository.findById(appointment2.
		 * getDiagnosticCenter().getCenterId()).get();
		 * 
		 * DiagnosticCenterDto diagnosticCenterDto2=new DiagnosticCenterDto();
		 * diagnosticCenterDto2.setCenterId(diagnosticCenter2.getCenterId());
		 * diagnosticCenterDto2.setCenterName(diagnosticCenter2.getCenterName());
		 * diagnosticCenterDto2.setContactNo(diagnosticCenter2.getContactNo());
		 * diagnosticCenterDto2.setAddress(diagnosticCenter2.getAddress());
		 * 
		 * appointmentDto2.setDiagnosticCenterDto(diagnosticCenterDto2);
		 * 
		 * return appointmentDto2;
		 * 
		 * 
		 * }
		 */
	
	
	
	
}
