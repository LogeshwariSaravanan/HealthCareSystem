package com.capgemini.healthcaresystem.entity;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="ListOfDiagnosticCenter")
public class DiagnosticCenter {
	@Id
	@Column(name="center_id")
	private String centerId;
	
	@Column(name="center_name")
	private String centerName;
	
	@Column(name="contact_no",unique = true,nullable = false)
	private BigInteger contactNo;
	
	@Column(name="address",unique = true)
	private String address;
	
	public DiagnosticCenter() {
		super();
	}

	
	public DiagnosticCenter(String centerId, String centerName, BigInteger contactNo, String address) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.contactNo = contactNo;
		this.address = address;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	

	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", contactNo=" + contactNo
				+ ", address=" + address + "]";
	}


	@PrePersist
	    public void generateUserId() {

	        if (centerId == null || centerId.isEmpty()) {

	            // Default prefix (first two letters)

	            String prefix = (String) centerName.subSequence(0, 3);

	            String uniquePart = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);

	            centerId = prefix + uniquePart;

	        }

	    }
}
