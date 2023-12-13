package com.capgemini.healthcaresystem.entity;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
	
	

	@Transient
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<Test> test;
	
	
//	@Transient
//	@OneToMany(cascade=CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	@JoinColumn(name="center_id")
//	private List<Test> test;


	
	@OneToMany(mappedBy = "diagnosticCenter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Appointment> appointment;
	
	
	public DiagnosticCenter() {
		super();
	}


	public DiagnosticCenter(String centerId, String centerName, BigInteger contactNo, String address, List<Test> test,
			List<Appointment> appointment) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.contactNo = contactNo;
		this.address = address;
		this.test = test;
		this.appointment = appointment;
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


	public List<Test> getTest() {
		return test;
	}


	public void setTest(List<Test> test) {
		this.test = test;
	}


	public List<Appointment> getAppointment() {
		return appointment;
	}


	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}


	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", contactNo=" + contactNo
				+ ", address=" + address + ", test=" + test + ", appointment=" + appointment + "]";
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
