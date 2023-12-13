package com.capgemini.healthcaresystem.service;

import java.math.BigInteger;
import java.util.List;

import com.capgemini.healthcaresystem.dto.AppointmentDto;
import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;

public interface UserService {
	public UserDto addUser(User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException, IdAlreadyExistException;
	public String login(User user) throws IdNotFoundException,InvalidPasswordException;
	public List<UserDto> getUser();
	public AppointmentDto makeAppointment(Appointment appointment)throws IdNotFoundException,IdNotFoundException, IdAlreadyExistException;
	public boolean approveAppointment(String userId, String diagnosticCenterId) throws InvalidUserException, IdNotFoundException;
	public String checkStatus(int appointmentId)throws IdNotFoundException;
	public String cancelAppointment(int appointmentId) throws IdNotFoundException;

}
