package com.capgemini.healthcaresystem.dto;

import java.math.BigInteger;
import java.util.List;

public class DiagnosticCenterDto {
    private String centerId;
	private String centerName;
	private BigInteger contactNo;
	private String address;
	private List<TestDto> listOfTest;
	
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

	
	
	

	
}
