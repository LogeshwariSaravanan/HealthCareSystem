package com.capgemini.healthcaresystem.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.healthcaresystem.entity.Appointment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class DiagnosticCenterDto {
    private String centerId;
	
	private String centerName;
	
	private BigInteger contactNo;
	
	private String address;
	
	private List<TestDto> listOfTest;
	
	private List<AppointmentDto> listOfAppointment;

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public BigInteger getContactNo() {
		return contactNo;
	}

	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<TestDto> getListOfTest() {
		return listOfTest;
	}

	public void setListOfTest(List<TestDto> listOfTest) {
		this.listOfTest = listOfTest;
	}

	public List<AppointmentDto> getListOfAppointment() {
		return listOfAppointment;
	}

	public void setListOfAppointment(List<AppointmentDto> listOfAppointment) {
		this.listOfAppointment = listOfAppointment;
	}
	
	

	
}
