package com.capgemini.healthcaresystem.service;

import java.util.List;

import com.capgemini.healthcaresystem.dto.UserDto;
import com.capgemini.healthcaresystem.entity.Appointment;
import com.capgemini.healthcaresystem.entity.User;
import com.capgemini.healthcaresystem.exception.IdAlreadyExistException;
import com.capgemini.healthcaresystem.exception.IdNotFoundException;
import com.capgemini.healthcaresystem.exception.InvalidContactNumberException;
import com.capgemini.healthcaresystem.exception.InvalidDateException;
import com.capgemini.healthcaresystem.exception.InvalidEmailIdException;
import com.capgemini.healthcaresystem.exception.InvalidPasswordException;
import com.capgemini.healthcaresystem.exception.InvalidUserException;
import com.capgemini.healthcaresystem.exception.InvalidUserNameException;

public interface UserService {
	public UserDto addUser(User user) throws InvalidUserNameException,InvalidPasswordException,InvalidContactNumberException,InvalidEmailIdException, IdAlreadyExistException;
	public String login(User user) throws IdNotFoundException,InvalidPasswordException;
	public List<UserDto> getUser();
	public Appointment makeAppointment(Appointment appointment)throws IdNotFoundException, IdAlreadyExistException,InvalidDateException;
	public String approveAppointment(String userId, String diagnosticCenterId) throws InvalidUserException, IdNotFoundException;
	public String checkStatus(int appointmentId)throws IdNotFoundException;
	public String cancelAppointment(int appointmentId) throws IdNotFoundException;

}
