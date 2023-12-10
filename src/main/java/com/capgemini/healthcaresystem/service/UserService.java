package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.User;

public interface UserService {
	public UserDto addUser(User user);
	public List<UserDto> getUser();
	public AppointmentDto makeAppointment(String userId, String diagnosticCenterid, String testId,LocalDateTime dateAndTime);

//	public boolean approveAppointment(String userId, BigInteger appointmentId) ;
	public boolean approveAppointment(String userId, String diagnosticCenterId);
	


}
