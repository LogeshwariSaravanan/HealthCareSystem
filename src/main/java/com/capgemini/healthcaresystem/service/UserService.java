package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;
import com.capgemini.healthcaresystem.exception.UserNotFoundException;

public interface UserService {
	public UserDto addUser(User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException;
	public List<UserDto> getUser();
	public AppointmentDto makeAppointment(String userId, String diagnosticCenterid, String testId,LocalDateTime dateAndTime)throws UserNotFoundException,IdNotFoundException;

	public boolean approveAppointment(String userId, String diagnosticCenterId) throws InvalidUserException, IdNotFoundException;
	


}
