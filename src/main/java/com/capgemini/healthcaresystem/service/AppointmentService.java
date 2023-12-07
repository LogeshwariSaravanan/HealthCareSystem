package com.capgemini.healthcaresystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Appointment addAppointment(Appointment appointment) {
		
		Test test = testRepository.findById(appointment.getTest().getTestid()).get();
		appointment.setTest(test);
		
		User user= userRepository.findById(appointment.getUser().getUserId()).get();
		appointment.setUser(user);
		
		DiagnosticCenter diagnosticCenter=diagnosticCenterRepository.findById(appointment.getDiagnosticCenter().getCenterId()).get();
		appointment.setDiagnosticCenter(diagnosticCenter);
		
		
		
		return  appointmentRepository.save(appointment);
	}

	
	
	
	
}
