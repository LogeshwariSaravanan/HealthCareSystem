package com.capgemini.healthcaresystem.dto;

import java.math.BigInteger;
import java.util.List;

public class DiagnosticCenterDto {
    private String centerId;
	private String centerName;
	private BigInteger contactNo;
	private String location;
	private List<TestDto> listOfTest;
	public DiagnosticCenterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiagnosticCenterDto(String centerId, String centerName, BigInteger contactNo, String location,
			List<TestDto> listOfTest) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.contactNo = contactNo;
		this.location = location;
		this.listOfTest = listOfTest;
	}
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<TestDto> getListOfTest() {
		return listOfTest;
	}
	public void setListOfTest(List<TestDto> listOfTest) {
		this.listOfTest = listOfTest;
	}
	@Override
	public String toString() {
		return "DiagnosticCenterDto [centerId=" + centerId + ", centerName=" + centerName + ", contactNo=" + contactNo
				+ ", location=" + location + ", listOfTest=" + listOfTest + "]";
	}
	
	
	
	
	

	
}
